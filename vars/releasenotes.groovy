import groovy.io.FileType;
import java.io.File;

@NonCPS
def call(Map config=[:]){
    println("Run script from 'releasenotes' library")

    println("config2: ${config}")

    def configSomeValue = config.someKey
    println("Run with config: 'someKey'='${configSomeValue}'")

    def path = "/var/jenkins_home/jobs/${env.JOB_NAME}/builds/${env.BUILD_NUMBER}"
    println("Build path is ${path}")
    def dir = new File(path);

    new File(dir.path + '/releasenotes.txt').withWriter('utf-8') {
        writer -> dir.eachFileRecurse(FileType.ANY){
            file ->
            if (file.isDirectory()){
                 writer.writeLine(file.name);
            } else {
                writer.writeLine('\t' + file.name + '\t' + file.length());
            }
        }
    }

}
