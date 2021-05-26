#!/usr/bin/env groovy
/*
 * Parse the JSON-LD of the examples.
 * Replace the http://purl.org/coar/notify context with the inline context in schema/notify.jsonld
 * Ouput a Turtle document
 *
 * Installation: OSX `brew install groovy` , Linux `yum install groovy`
 *
 * Usage: ./test/parse.groovy examples/accept.jsonld
 */
@Grab(group='org.topbraid', module='shacl', version='1.3.2')
@Grab(group='commons-io', module='commons-io', version='2.8.0')
@Grab(group='org.apache.jena', module='jena-core', version='3.13.1')
@Grab(group='org.slf4j', module='slf4j-api', version='1.7.30')
@Grab(group='org.slf4j', module='slf4j-simple', version='1.7.30')
@Grab(group='com.github.jsonld-java', module='jsonld-java', version='0.9.0')

import org.apache.jena.rdf.model.Model
import org.apache.jena.util.FileUtils
import org.apache.jena.rdf.model.Resource
import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.riot.RDFDataMgr
import org.apache.jena.riot.RDFFormat
import java.io.FileInputStream
import java.io.ByteArrayInputStream
import groovy.json.JsonSlurper
import groovy.json.JsonOutput

def parseJson(filename) {
    return new JsonSlurper().parse(new FileInputStream(filename))
}

def loadModel(json) {
    Model model = ModelFactory.createDefaultModel()
    model.read(new ByteArrayInputStream(json.getBytes('UTF-8')),"urn:dummy","JSONLD")
    return model;
}

if (args.size() != 1) {
   System.err.println("usage: parse.groovy file")
   System.exit(1)
}

def NOTIFY_IRI = "http://purl.org/coar/notify"
def data     = parseJson(args[0])
def context  = parseJson("schema/notify.jsonld")

// Replace NOTIFY_IRI with contents of the local schema
def new_context = [];

data['@context'].each {

  if (it == NOTIFY_IRI) {
      new_context.push(context['@context']);
  }
  else {
      new_context.push(it)
  }
}

data['@context'] = new_context

def dataModel = loadModel(JsonOutput.toJson(data))

RDFDataMgr.write(System.out, dataModel, RDFFormat.TURTLE);
