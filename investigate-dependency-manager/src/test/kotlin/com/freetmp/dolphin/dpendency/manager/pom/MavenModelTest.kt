package com.freetmp.dolphin.dpendency.manager.pom

import com.freetmp.dolphin.dependency.manager.config.Configuration
import com.freetmp.dolphin.dependency.manager.pom.RepositoryModelResolver
import org.apache.maven.model.building.*
import org.assertj.core.api.Assertions.assertThat
import java.io.File
import org.junit.Test as test

/**
 * Created by LiuPin on 2015/6/8.
 */
class MavenModelTest {

  @test fun testModuleBuild() {
    val mbr: ModelBuildingRequest = DefaultModelBuildingRequest()
    mbr.isProcessPlugins = false
    val url = MavenModelTest::class.java.classLoader.getResource("spring-boot-starter-test-1.2.4.RELEASE.pom.xml")
    mbr.pomFile = File(url.path)
    mbr.modelResolver = RepositoryModelResolver(Configuration { localRepo = "target/lib" })
    mbr.validationLevel = ModelBuildingRequest.VALIDATION_LEVEL_MINIMAL
    var model = DefaultModelBuilderFactory().newInstance().build(mbr).effectiveModel

    assertThat(model.dependencies).hasSize(6)
    val versions = arrayListOf("4.12", "1.10.19", "1.3", "1.3", "4.1.6.RELEASE", "4.1.6.RELEASE")
    model.dependencies.forEachIndexed { i, it -> assertThat(it.version).isEqualToIgnoringCase(versions[i]) }
  }
}