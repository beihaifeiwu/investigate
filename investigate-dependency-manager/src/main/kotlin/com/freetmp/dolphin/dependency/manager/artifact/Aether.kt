package com.freetmp.dolphin.dependency.manager.artifact

import com.freetmp.dolphin.dependency.manager.config.*
import org.apache.maven.repository.internal.MavenRepositorySystemUtils
import org.eclipse.aether.*
import org.eclipse.aether.artifact.Artifact
import org.eclipse.aether.artifact.DefaultArtifact
import org.eclipse.aether.collection.CollectRequest
import org.eclipse.aether.connector.basic.BasicRepositoryConnectorFactory
import org.eclipse.aether.deployment.DeployRequest
import org.eclipse.aether.graph.Dependency
import org.eclipse.aether.graph.DependencyNode
import org.eclipse.aether.impl.DefaultServiceLocator
import org.eclipse.aether.installation.InstallRequest
import org.eclipse.aether.repository.LocalRepository
import org.eclipse.aether.resolution.*
import org.eclipse.aether.spi.connector.RepositoryConnectorFactory
import org.eclipse.aether.spi.connector.transport.TransporterFactory
import org.eclipse.aether.transport.file.FileTransporterFactory
import org.eclipse.aether.transport.http.HttpTransporterFactory
import org.eclipse.aether.util.artifact.JavaScopes
import org.eclipse.aether.util.filter.DependencyFilterUtils
import org.eclipse.aether.util.graph.manager.DependencyManagerUtils
import org.eclipse.aether.util.graph.transformer.ConflictResolver
import org.eclipse.aether.util.graph.visitor.PreorderNodeListGenerator
import java.io.*

/**
 * Created by LiuPin on 2015/6/9.
 */
fun newRepositorySystem(): RepositorySystem {
  val locator: DefaultServiceLocator = MavenRepositorySystemUtils.newServiceLocator()
  locator.addService(RepositoryConnectorFactory::class.java, BasicRepositoryConnectorFactory::class.java)
  locator.addService(TransporterFactory::class.java, FileTransporterFactory::class.java)
  locator.addService(TransporterFactory::class.java, HttpTransporterFactory::class.java)

  locator.setErrorHandler(object : DefaultServiceLocator.ErrorHandler() {
    override fun serviceCreationFailed(type: Class<*>?, impl: Class<*>?, exception: Throwable?) {
      exception?.printStackTrace()
    }
  })

  return locator.getService(RepositorySystem::class.java)
}

fun newRepositorySystemSession(system: RepositorySystem, config: Configuration): DefaultRepositorySystemSession {
  val session = MavenRepositorySystemUtils.newSession()
  val localRepo = LocalRepository(config.localRepo)
  session.localRepositoryManager = system.newLocalRepositoryManager(session, localRepo)

  session.transferListener = ConsoleTransferListener()
  session.repositoryListener = ConsoleRepositoryListener()
  return session
}

fun displayTree(node: DependencyNode) = node.accept(ConsoleDependencyGraphDumper())

data class ResolveResult(val root: DependencyNode, val resolvedFiles: List<File>, val resolvedClassPath: String)

inline fun <T> template(full: String, config: Configuration, run: (RepositorySystem, RepositorySystemSession, Artifact) -> T): T {
  val system = newRepositorySystem()
  val session = newRepositorySystemSession(system, config)
  val artifact = DefaultArtifact(full)

  return run(system, session, artifact)
}

inline fun template(config: Configuration, run: (RepositorySystem, RepositorySystemSession) -> Unit) {
  val system = newRepositorySystem()
  val session = newRepositorySystemSession(system, config)
  run(system, session)
}

fun resolve(full: String, config: Configuration): ResolveResult {
  return template<ResolveResult>(full, config) { system, session, artifact ->
    val dependency = Dependency(artifact, JavaScopes.RUNTIME)

    val collectRequest = CollectRequest()
    collectRequest.root = dependency
    config.fillReposTo { collectRequest.addRepository(it) }

    val dependencyRequest = DependencyRequest()
    dependencyRequest.collectRequest = collectRequest
    val rootNode = system.resolveDependencies(session, dependencyRequest).root
    val nlg = PreorderNodeListGenerator()
    rootNode.accept(nlg)

    return ResolveResult(rootNode, nlg.files, nlg.classPath)
  }
}

fun resolveArtifact(full: String, config: Configuration): Artifact {
  return template(full, config) { system, session, artifact ->
    val request = ArtifactRequest()
    request.artifact = artifact
    config.fillReposTo { request.addRepository(it) }

    val artifactResult = system.resolveArtifact(session, request)
    artifactResult.artifact
  }

}

fun resolveTransitiveDependencies(full: String, config: Configuration): List<Artifact> {
  return template(full, config) { system, session, artifact ->
    val filter = DependencyFilterUtils.classpathFilter(JavaScopes.COMPILE)
    val collectRequest = CollectRequest()
    collectRequest.root = Dependency(artifact, JavaScopes.COMPILE)
    config.fillReposTo { collectRequest.addRepository(it) }
    val dependencyRequest = DependencyRequest(collectRequest, filter)
    system.resolveDependencies(session, dependencyRequest).artifactResults.map { it.artifact }
  }
}

fun getDependencyHierarchy(full: String, config: Configuration): String {
  return template(full, config) { system, session, artifact ->
    (session as DefaultRepositorySystemSession).setConfigProperty(ConflictResolver.CONFIG_PROP_VERBOSE, true)
    session.setConfigProperty(DependencyManagerUtils.CONFIG_PROP_VERBOSE, true)

    val adr = ArtifactDescriptorRequest()
    adr.artifact = artifact
    config.fillReposTo { adr.addRepository(it) }
    val adResult = system.readArtifactDescriptor(session, adr)

    val collectRequest = CollectRequest()
    collectRequest.setRootArtifact(adResult.artifact).setDependencies(adResult.dependencies)
        .setManagedDependencies(adResult.managedDependencies).repositories = adResult.repositories

    val collectResult = system.collectDependencies(session, collectRequest)

    val out = ByteArrayOutputStream()
    collectResult.root.accept(ConsoleDependencyGraphDumper(PrintStream(out)))
    out.toString()
  }
}

fun getDependencyTree(full: String, config: Configuration): String {
  return template(full,config){ system, session, artifact ->
    val collectRequest = CollectRequest()
    collectRequest.root = Dependency(artifact,"")
    config.fillReposTo { collectRequest.addRepository(it) }
    val collectResult = system.collectDependencies(session, collectRequest)
    val out = ByteArrayOutputStream()
    collectResult.root.accept(ConsoleDependencyGraphDumper(PrintStream(out)))
    out.toString()
  }
}

fun install(artifact: Artifact, pom: Artifact, config: Configuration) {
  template(config) { system, session ->
    val installRequest: InstallRequest = InstallRequest()
    installRequest.addArtifact(artifact).addArtifact(pom)
    system.install(session, installRequest)
  }

}

fun deploy(artifact: Artifact, pom: Artifact, config: Configuration) {
  template(config) { system, session ->
    val deployRequest = DeployRequest()
    deployRequest.addArtifact(artifact).addArtifact(pom)
    config.fillDeployRepoTo { deployRequest.repository = it }
    system.deploy(session, deployRequest)
  }
}

fun availableVersions(groupId: String, artifactId: String, config: Configuration): VersionRangeResult {
  return template("$groupId:$artifactId:[0,)", config) { system, session, artifact ->
    val vrr = VersionRangeRequest()
    vrr.artifact = artifact
    config.fillReposTo { vrr.addRepository(it) }
    system.resolveVersionRange(session, vrr)
  }
}

