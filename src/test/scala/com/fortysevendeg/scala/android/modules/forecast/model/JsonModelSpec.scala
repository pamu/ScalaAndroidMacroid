/*
 * Copyright (C) 2015 47 Degrees, LLC http://47deg.com hello@47deg.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fortysevendeg.scala.android.modules.forecast.model

import com.fortysevendeg.scala.android.BaseTestSpecification
import play.api.libs.json.{JsValue, Json}
import JsonImplicits._

object JsonImplicits {

  implicit val apiCloudsReads = Json.reads[ApiClouds]
  implicit val apiWindReads = Json.reads[ApiWind]
  implicit val apiWeatherReads = Json.reads[ApiWeather]
  implicit val apiMainReads = Json.reads[ApiMain]
  implicit val apiSysReads = Json.reads[ApiSys]
  implicit val apiCoordReads = Json.reads[ApiCoord]
  implicit val apiModelReads = Json.reads[ApiModel]

}

class JsonModelSpec
  extends BaseTestSpecification {

  "load and map sample json" should {

    "return an ApiModel class with the right fields" in new BaseTestScope {


      val jsonSource = scala.io.Source.fromInputStream(JsonImplicits.getClass.getResourceAsStream("/weather.json")).mkString
      val json: JsValue = Json.parse(jsonSource)
      val jsonValue = json.as[ApiModel]

      jsonValue.weather.size shouldEqual 1
      jsonValue.name shouldEqual "Seattle"
    }

  }

}
