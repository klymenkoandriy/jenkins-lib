def call(Map config[:]) {

    pipeline {
        agent any

        stages {
            stage("build") {
                steps {
                    sh "./mvnw package"
                }
            }
            stage("capture") {
                steps {
                    archiveArtifacts '**/target/*.jar'
                    junit '**/target/surefire-reports/*.xml'
                }
            }
            stage("my-lib-report") {
                steps {
                    releasenotes()
                }
            }
        }
    }
}
