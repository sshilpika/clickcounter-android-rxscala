// Slightly complicated build file for use with pfn's excellent
// Android Scala sbt plugin.
//
// Please see here for details:
// https://github.com/pfn/android-sdk-plugin/blob/master/README.md

// TODO support for out-of-container system testing using Robolectric or similar (look at https://gist.github.com/pfn/2503441)

import android.Keys._

android.Plugin.androidBuild

name := "clickcounter-android-rxscala"

version := "0.2.1"

scalacOptions in Compile += "-feature"

platformTarget in Android := "android-19"

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.11",
  "org.scalamock" %% "scalamock-scalatest-support" % "3.1.RC1",
  "org.scalatest" % "scalatest_2.10" % "2.2.1",
  "com.netflix.rxjava" % "rxjava-core" % "0.20.3",
  "com.netflix.rxjava" % "rxjava-scala" % "0.20.4",
  "com.netflix.rxjava" % "rxjava-android" % "0.20.4"
)

// With this option, we cannot have dependencies in the test scope!
debugIncludesTests in Android := true

// Optional: turn off almost all the warnings.
proguardOptions in Android ++= Seq(
  "-dontwarn rx.internal.util.**",
  "-dontwarn org.scalatest.**",
  "-dontwarn android.test.**"
)

// Required so Proguard won't remove the actual instrumentation tests.
proguardOptions in Android ++= Seq(
  "-keep public class * extends junit.framework.TestCase",
  "-keepclassmembers class * extends junit.framework.TestCase { *; }"
)

apkbuildExcludes in Android += "LICENSE.txt"

exportJars in Test := false