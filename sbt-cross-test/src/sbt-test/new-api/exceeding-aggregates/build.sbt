import sbtcross.{crossProject, CrossType}

lazy val bar = crossProject(JSPlatform, JVMPlatform, NativePlatform)
  .crossType(CrossType.Pure)
  .settings(scalaVersion := "2.11.8")
  .nativeSettings(resolvers += Resolver.sonatypeRepo("snapshots"))

lazy val barJS     = bar.js
lazy val barJVM    = bar.jvm
lazy val barNative = bar.native

lazy val buzz = crossProject(JSPlatform, JVMPlatform, NativePlatform)
  .crossType(CrossType.Pure)
  .settings(scalaVersion := "2.11.8")
  .nativeSettings(resolvers += Resolver.sonatypeRepo("snapshots"))

lazy val buzzJS     = buzz.js
lazy val buzzJVM    = buzz.jvm
lazy val buzzNative = buzz.native

// bar & buzz defines JSPlatform and is dropped
lazy val foo = crossProject(JVMPlatform, NativePlatform)
  .settings(scalaVersion := "2.11.8")
  .nativeSettings(resolvers += Resolver.sonatypeRepo("snapshots"))
  .aggregate(bar, buzz)
  .dependsOn(bar, buzz)

lazy val fooJVM = foo.jvm
lazy val fooNative = foo.native
