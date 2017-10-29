package controllers

import java.io.{BufferedReader, FileReader}
import java.util.stream.Collectors

import play.api.mvc.{Action, Controller}
import org.mitre.synthea.engine.Generator
import play.api.libs.json.JsValue

import scala.collection.JavaConversions._
import play.libs.Json

object Application extends Controller {
  def index = Action {
    Ok(views.html.index("Hello Play Framework"))
  }
  def generate_data=Action{ implicit request=>
    println(request.body.toString)
    val dataRequestJson=request.body.asJson.get
    val populationSize=dataRequestJson.\("population").as[Int]
    val demographicConfig=dataRequestJson.\("demographics_config").toString()

    val demoGraphicConfigWithCity=s"""{"Dedham":$demographicConfig}"""
    val generator=new Generator(populationSize,demoGraphicConfigWithCity)
    val reponseUrl=generator.run()
    val opMap=mapAsJavaMap(Map("bucket_url"->reponseUrl))
    Ok(Json.stringify(Json.toJson(opMap))).withHeaders("Access-Control-Allow-Origin" -> "*",
      "Access-Control-Expose-Headers" -> "WWW-Authenticate, Server-Authorization",
      "Access-Control-Allow-Methods" -> "POST, GET, OPTIONS",
      "Access-Control-Allow-Headers" -> "Origing,x-requested-with,content-type,Cache-Control,Pragma,Date,Authorization")
  }
  def gen_data_option=Action{implicit request=>
      Ok("Done").withHeaders("Access-Control-Allow-Origin" -> "*",
      "Access-Control-Expose-Headers" -> "WWW-Authenticate, Server-Authorization",
      "Access-Control-Allow-Methods" -> "POST, GET, OPTIONS",
      "Access-Control-Allow-Headers" -> "Origing,x-requested-with,content-type,Cache-Control,Pragma,Date,Authorization")
  }
}