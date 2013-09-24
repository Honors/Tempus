package com.mattneary.tempus

import org.scalatra._
import scalate.ScalateSupport
import scalaj.http.{Http, HttpOptions}

import scala.io.Source
import java.io._
import org.slf4j.{Logger, LoggerFactory}
import java.nio._
import net.liftweb.json._
import net.liftweb.json.JsonDSL._
import java.io.{File,FileInputStream,FileOutputStream}

class TempusServlet extends TempusStack {
  val dest = "/Users/mattneary/Desktop/TempusServer/"
  val file = "index.html"
  def renderPage(file: String) {
    org.scalatra.util.io.copy(new FileInputStream(file), response.getOutputStream)
  }

  get("/") {
    renderPage(dest + file)
  }
  get("/asset/*") {
    val rest = multiParams("splat")
    val file = rest.mkString("/")
    renderPage(dest + file)
  }
  post("/submit") {
    contentType = "text/plaintext"
    response.getOutputStream.print(request.getParameter("break"))
    response.getOutputStream.close()
  }
}
