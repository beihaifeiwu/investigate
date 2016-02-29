package com.freetmp.investigate.kotlin.jwt

import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.JWSHeader
import com.nimbusds.jose.crypto.RSASSASigner
import com.nimbusds.jose.crypto.RSASSAVerifier
import com.nimbusds.jwt.JWTClaimsSet
import com.nimbusds.jwt.SignedJWT
import java.security.KeyPairGenerator
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.util.*

/**
 * Created by LiuPin on 2015/6/25.
 */
fun main(args: Array<String>) {
  val keyGenerator = KeyPairGenerator.getInstance("RSA")
  keyGenerator.initialize(1024)

  val kp = keyGenerator.genKeyPair()
  val publicKey = kp.public as RSAPublicKey
  val privateKey = kp.private as RSAPrivateKey

  val signer = RSASSASigner(privateKey)

  val claimsSet = JWTClaimsSet
      .Builder()
      .subject("alice")
      .issuer("https://c2id.com")
      .expirationTime(Date(Date().time + 60 * 1000))
      .build()

  var singedJWT = SignedJWT(JWSHeader(JWSAlgorithm.RS256), claimsSet)
  singedJWT.sign(signer)

  val s = singedJWT.serialize()

  println(s)

  singedJWT = SignedJWT.parse(s)

  val verifier = RSASSAVerifier(publicKey)
  assert(singedJWT.verify(verifier))

  assert("alice" == singedJWT.jwtClaimsSet.subject)
  assert("https://c2id.com" == singedJWT.jwtClaimsSet.issuer)
  assert(Date().before(singedJWT.jwtClaimsSet.expirationTime))
}
