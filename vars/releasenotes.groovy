import groovy.io.FileType;
import java.io.File;

def call(Map config=[:]){
    println("Run script from 'releasenotes' library")
    println("whoami".execute().text)

    println("Current workspace path is ${env.WORKSPACE}")
    println("Current job is ${env.JOB_NAME}")

    def dir = new File(".");


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
