Service: Weather
================
Endpoints
---------
### WeatherSoap
**Address:** http://wsf.cdyne.com/WeatherWS/Weather.asmx
<br />**Protocol:** SOAP
<br />
#### Operations
##### GetWeatherInformation
**Description:** Gets Information for each WeatherID
<br />**Pattern:** IN_OUT
<br />**Input:** [GetWeatherInformationSoapIn](#GetWeatherInformationSoapIn)
<br />**Output:** [GetWeatherInformationSoapOut](#GetWeatherInformationSoapOut)
<br />**Faults:** none
##### GetCityForecastByZIP
**Description:** Allows you to get your City Forecast Over the Next 7 Days, which is updated hourly. U.S. Only
<br />**Pattern:** IN_OUT
<br />**Input:** [GetCityForecastByZIPSoapIn](#GetCityForecastByZIPSoapIn)
<br />**Output:** [GetCityForecastByZIPSoapOut](#GetCityForecastByZIPSoapOut)
<br />**Faults:** none
##### GetCityWeatherByZIP
**Description:** Allows you to get your City's Weather, which is updated hourly. U.S. Only
<br />**Pattern:** IN_OUT
<br />**Input:** [GetCityWeatherByZIPSoapIn](#GetCityWeatherByZIPSoapIn)
<br />**Output:** [GetCityWeatherByZIPSoapOut](#GetCityWeatherByZIPSoapOut)
<br />**Faults:** none
### WeatherSoap12
**Address:** http://wsf.cdyne.com/WeatherWS/Weather.asmx
<br />**Protocol:** SOAP
<br />
#### Operations
##### GetWeatherInformation
**Description:** Gets Information for each WeatherID
<br />**Pattern:** IN_OUT
<br />**Input:** [GetWeatherInformationSoapIn](#GetWeatherInformationSoapIn)
<br />**Output:** [GetWeatherInformationSoapOut](#GetWeatherInformationSoapOut)
<br />**Faults:** none
##### GetCityForecastByZIP
**Description:** Allows you to get your City Forecast Over the Next 7 Days, which is updated hourly. U.S. Only
<br />**Pattern:** IN_OUT
<br />**Input:** [GetCityForecastByZIPSoapIn](#GetCityForecastByZIPSoapIn)
<br />**Output:** [GetCityForecastByZIPSoapOut](#GetCityForecastByZIPSoapOut)
<br />**Faults:** none
##### GetCityWeatherByZIP
**Description:** Allows you to get your City's Weather, which is updated hourly. U.S. Only
<br />**Pattern:** IN_OUT
<br />**Input:** [GetCityWeatherByZIPSoapIn](#GetCityWeatherByZIPSoapIn)
<br />**Output:** [GetCityWeatherByZIPSoapOut](#GetCityWeatherByZIPSoapOut)
<br />**Faults:** none
### WeatherHttpGet
**Address:** http://wsf.cdyne.com/WeatherWS/Weather.asmx
<br />**Protocol:** HTTP
<br />**Verb:** GET
<br />
#### Operations
##### GetWeatherInformation
**Description:** Gets Information for each WeatherID
<br />**Pattern:** IN_OUT
<br />**Input:** none
<br />**Output:** [GetWeatherInformationHttpGetOut](#GetWeatherInformationHttpGetOut)
<br />**Faults:** none
##### GetCityForecastByZIP
**Description:** Allows you to get your City Forecast Over the Next 7 Days, which is updated hourly. U.S. Only
<br />**Pattern:** IN_OUT
<br />**Input:** string
<br />**Output:** [GetCityForecastByZIPHttpGetOut](#GetCityForecastByZIPHttpGetOut)
<br />**Faults:** none
##### GetCityWeatherByZIP
**Description:** Allows you to get your City's Weather, which is updated hourly. U.S. Only
<br />**Pattern:** IN_OUT
<br />**Input:** string
<br />**Output:** [GetCityWeatherByZIPHttpGetOut](#GetCityWeatherByZIPHttpGetOut)
<br />**Faults:** none
### WeatherHttpPost
**Address:** http://wsf.cdyne.com/WeatherWS/Weather.asmx
<br />**Protocol:** HTTP
<br />**Verb:** POST
<br />
#### Operations
##### GetWeatherInformation
**Description:** Gets Information for each WeatherID
<br />**Pattern:** IN_OUT
<br />**Input:** none
<br />**Output:** [GetWeatherInformationHttpPostOut](#GetWeatherInformationHttpPostOut)
<br />**Faults:** none
##### GetCityForecastByZIP
**Description:** Allows you to get your City Forecast Over the Next 7 Days, which is updated hourly. U.S. Only
<br />**Pattern:** IN_OUT
<br />**Input:** string
<br />**Output:** [GetCityForecastByZIPHttpPostOut](#GetCityForecastByZIPHttpPostOut)
<br />**Faults:** none
##### GetCityWeatherByZIP
**Description:** Allows you to get your City's Weather, which is updated hourly. U.S. Only
<br />**Pattern:** IN_OUT
<br />**Input:** string
<br />**Output:** [GetCityWeatherByZIPHttpPostOut](#GetCityWeatherByZIPHttpPostOut)
<br />**Faults:** none

Messages
--------
<a id=GetWeatherInformationSoapIn></a>GetWeatherInformationSoapIn<br/>
**Parts:**
- **Name**: parameters
- **Type**: [GetWeatherInformation](#GetWeatherInformation)

<a id=GetWeatherInformationSoapOut></a>GetWeatherInformationSoapOut<br/>
**Parts:**
- **Name**: parameters
- **Type**: [GetWeatherInformationResponse](#GetWeatherInformationResponse)

<a id=GetCityForecastByZIPSoapIn></a>GetCityForecastByZIPSoapIn<br/>
**Parts:**
- **Name**: parameters
- **Type**: [GetCityForecastByZIP](#GetCityForecastByZIP)

<a id=GetCityForecastByZIPSoapOut></a>GetCityForecastByZIPSoapOut<br/>
**Parts:**
- **Name**: parameters
- **Type**: [GetCityForecastByZIPResponse](#GetCityForecastByZIPResponse)

<a id=GetCityWeatherByZIPSoapIn></a>GetCityWeatherByZIPSoapIn<br/>
**Parts:**
- **Name**: parameters
- **Type**: [GetCityWeatherByZIP](#GetCityWeatherByZIP)

<a id=GetCityWeatherByZIPSoapOut></a>GetCityWeatherByZIPSoapOut<br/>
**Parts:**
- **Name**: parameters
- **Type**: [GetCityWeatherByZIPResponse](#GetCityWeatherByZIPResponse)

<a id=GetWeatherInformationHttpGetIn></a>GetWeatherInformationHttpGetIn<br/>

<a id=GetWeatherInformationHttpGetOut></a>GetWeatherInformationHttpGetOut<br/>
**Parts:**
- **Name**: Body
- **Type**: [ArrayOfWeatherDescription](#ArrayOfWeatherDescription)

<a id=GetCityForecastByZIPHttpGetIn></a>GetCityForecastByZIPHttpGetIn<br/>
**Parts:**
- **Name**: ZIP
- **Type**: [string](#string)

<a id=GetCityForecastByZIPHttpGetOut></a>GetCityForecastByZIPHttpGetOut<br/>
**Parts:**
- **Name**: Body
- **Type**: [ForecastReturn](#ForecastReturn)

<a id=GetCityWeatherByZIPHttpGetIn></a>GetCityWeatherByZIPHttpGetIn<br/>
**Parts:**
- **Name**: ZIP
- **Type**: [string](#string)

<a id=GetCityWeatherByZIPHttpGetOut></a>GetCityWeatherByZIPHttpGetOut<br/>
**Parts:**
- **Name**: Body
- **Type**: [WeatherReturn](#WeatherReturn)

<a id=GetWeatherInformationHttpPostIn></a>GetWeatherInformationHttpPostIn<br/>

<a id=GetWeatherInformationHttpPostOut></a>GetWeatherInformationHttpPostOut<br/>
**Parts:**
- **Name**: Body
- **Type**: [ArrayOfWeatherDescription](#ArrayOfWeatherDescription)

<a id=GetCityForecastByZIPHttpPostIn></a>GetCityForecastByZIPHttpPostIn<br/>
**Parts:**
- **Name**: ZIP
- **Type**: [string](#string)

<a id=GetCityForecastByZIPHttpPostOut></a>GetCityForecastByZIPHttpPostOut<br/>
**Parts:**
- **Name**: Body
- **Type**: [ForecastReturn](#ForecastReturn)

<a id=GetCityWeatherByZIPHttpPostIn></a>GetCityWeatherByZIPHttpPostIn<br/>
**Parts:**
- **Name**: ZIP
- **Type**: [string](#string)

<a id=GetCityWeatherByZIPHttpPostOut></a>GetCityWeatherByZIPHttpPostOut<br/>
**Parts:**
- **Name**: Body
- **Type**: [WeatherReturn](#WeatherReturn)


Datatypes
---------
<a id=ArrayOfForecast></a>ArrayOfForecast
- **Name**: Forecast
<br />**Type**: Forecast
<br />**Cardinality**: 0..unbounded
<br />

<a id=GetCityForecastByZIPResponse></a>GetCityForecastByZIPResponse
- **Name**: GetCityForecastByZIPResult
<br />**Type**: ForecastReturn
<br />**Cardinality**: 0..1
<br />

<a id=GetWeatherInformation></a>GetWeatherInformation
<br /><br />
<a id=GetWeatherInformationResponse></a>GetWeatherInformationResponse
- **Name**: GetWeatherInformationResult
<br />**Type**: ArrayOfWeatherDescription
<br />**Cardinality**: 0..1
<br />

<a id=temp></a>temp
- **Name**: MorningLow
<br />**Type**: string
<br />**Cardinality**: 0..1
<br />
- **Name**: DaytimeHigh
<br />**Type**: string
<br />**Cardinality**: 0..1
<br />

<a id=WeatherDescription></a>WeatherDescription
- **Name**: WeatherID
<br />**Type**: short
<br />**Cardinality**: 1..1
<br />
- **Name**: Description
<br />**Type**: string
<br />**Cardinality**: 0..1
<br />
- **Name**: PictureURL
<br />**Type**: string
<br />**Cardinality**: 0..1
<br />

<a id=GetCityWeatherByZIP></a>GetCityWeatherByZIP
- **Name**: ZIP
<br />**Type**: string
<br />**Cardinality**: 0..1
<br />

<a id=Forecast></a>Forecast
- **Name**: Date
<br />**Type**: dateTime
<br />**Cardinality**: 1..1
<br />
- **Name**: WeatherID
<br />**Type**: short
<br />**Cardinality**: 1..1
<br />
- **Name**: Desciption
<br />**Type**: string
<br />**Cardinality**: 0..1
<br />
- **Name**: Temperatures
<br />**Type**: temp
<br />**Cardinality**: 1..1
<br />
- **Name**: ProbabilityOfPrecipiation
<br />**Type**: POP
<br />**Cardinality**: 1..1
<br />

<a id=GetCityForecastByZIP></a>GetCityForecastByZIP
- **Name**: ZIP
<br />**Type**: string
<br />**Cardinality**: 0..1
<br />

<a id=short></a>short
<br /><br />
<a id=GetCityWeatherByZIPResponse></a>GetCityWeatherByZIPResponse
- **Name**: GetCityWeatherByZIPResult
<br />**Type**: WeatherReturn
<br />**Cardinality**: 1..1
<br />

<a id=ArrayOfWeatherDescription></a>ArrayOfWeatherDescription
- **Name**: WeatherDescription
<br />**Type**: WeatherDescription
<br />**Cardinality**: 0..unbounded
<br />

<a id=ForecastReturn></a>ForecastReturn
- **Name**: Success
<br />**Type**: boolean
<br />**Cardinality**: 1..1
<br />
- **Name**: ResponseText
<br />**Type**: string
<br />**Cardinality**: 0..1
<br />
- **Name**: State
<br />**Type**: string
<br />**Cardinality**: 0..1
<br />
- **Name**: City
<br />**Type**: string
<br />**Cardinality**: 0..1
<br />
- **Name**: WeatherStationCity
<br />**Type**: string
<br />**Cardinality**: 0..1
<br />
- **Name**: ForecastResult
<br />**Type**: ArrayOfForecast
<br />**Cardinality**: 0..1
<br />

<a id=boolean></a>boolean
<br /><br />
<a id=dateTime></a>dateTime
<br /><br />
<a id=POP></a>POP
- **Name**: Nighttime
<br />**Type**: string
<br />**Cardinality**: 0..1
<br />
- **Name**: Daytime
<br />**Type**: string
<br />**Cardinality**: 0..1
<br />

<a id=WeatherReturn></a>WeatherReturn
- **Name**: Success
<br />**Type**: boolean
<br />**Cardinality**: 1..1
<br />
- **Name**: ResponseText
<br />**Type**: string
<br />**Cardinality**: 0..1
<br />
- **Name**: State
<br />**Type**: string
<br />**Cardinality**: 0..1
<br />
- **Name**: City
<br />**Type**: string
<br />**Cardinality**: 0..1
<br />
- **Name**: WeatherStationCity
<br />**Type**: string
<br />**Cardinality**: 0..1
<br />
- **Name**: WeatherID
<br />**Type**: short
<br />**Cardinality**: 1..1
<br />
- **Name**: Description
<br />**Type**: string
<br />**Cardinality**: 0..1
<br />
- **Name**: Temperature
<br />**Type**: string
<br />**Cardinality**: 0..1
<br />
- **Name**: RelativeHumidity
<br />**Type**: string
<br />**Cardinality**: 0..1
<br />
- **Name**: Wind
<br />**Type**: string
<br />**Cardinality**: 0..1
<br />
- **Name**: Pressure
<br />**Type**: string
<br />**Cardinality**: 0..1
<br />
- **Name**: Visibility
<br />**Type**: string
<br />**Cardinality**: 0..1
<br />
- **Name**: WindChill
<br />**Type**: string
<br />**Cardinality**: 0..1
<br />
- **Name**: Remarks
<br />**Type**: string
<br />**Cardinality**: 0..1
<br />

<a id=string></a>string
<br /><br />
