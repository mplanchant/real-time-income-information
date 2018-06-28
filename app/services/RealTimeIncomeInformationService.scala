/*
 * Copyright 2018 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package services

import models.response.DesSuccessResponse
import play.api.libs.json.{JsValue, Json, _}

class RealTimeIncomeInformationService {

  def pickOneValue(key: String, json: JsValue): (String, JsValue) = {
    json.transform((__ \\ key).json.pick[JsValue]).asOpt match {
      case Some(x) => key -> x
      case None => key -> JsString("undefined")
    }
  }

  def pickAll(keys: List[String], desResponse: DesSuccessResponse): JsValue = {
    val transformedJson = desResponse.response.transform((__ \\ "taxYears").json.pick[JsArray])
    val listOfTaxYears = transformedJson.get.as[List[JsValue]]
    val requestedValues = listOfTaxYears.map(taxYear => keys.map(key =>  pickOneValue(key, taxYear)).toMap)
    Json.toJson(Map("taxYears" -> requestedValues))
  }

}
