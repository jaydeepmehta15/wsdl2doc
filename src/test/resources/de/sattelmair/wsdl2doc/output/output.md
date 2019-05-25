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
**Parts:**<br/>
**Name**: parameters<br />
**Type**: [GetWeatherInformation](#GetWeatherInformation)<br />

<a id=GetWeatherInformationSoapOut></a>GetWeatherInformationSoapOut<br/>
**Parts:**<br/>
**Name**: parameters<br />
**Type**: [GetWeatherInformationResponse](#GetWeatherInformationResponse)<br />

<a id=GetCityForecastByZIPSoapIn></a>GetCityForecastByZIPSoapIn<br/>
**Parts:**<br/>
**Name**: parameters<br />
**Type**: [GetCityForecastByZIP](#GetCityForecastByZIP)<br />

<a id=GetCityForecastByZIPSoapOut></a>GetCityForecastByZIPSoapOut<br/>
**Parts:**<br/>
**Name**: parameters<br />
**Type**: [GetCityForecastByZIPResponse](#GetCityForecastByZIPResponse)<br />

<a id=GetCityWeatherByZIPSoapIn></a>GetCityWeatherByZIPSoapIn<br/>
**Parts:**<br/>
**Name**: parameters<br />
**Type**: [GetCityWeatherByZIP](#GetCityWeatherByZIP)<br />

<a id=GetCityWeatherByZIPSoapOut></a>GetCityWeatherByZIPSoapOut<br/>
**Parts:**<br/>
**Name**: parameters<br />
**Type**: [GetCityWeatherByZIPResponse](#GetCityWeatherByZIPResponse)<br />

<a id=GetWeatherInformationHttpGetIn></a>GetWeatherInformationHttpGetIn<br/>

<a id=GetWeatherInformationHttpGetOut></a>GetWeatherInformationHttpGetOut<br/>
**Parts:**<br/>
**Name**: Body<br />
**Type**: [ArrayOfWeatherDescription](#ArrayOfWeatherDescription)<br />

<a id=GetCityForecastByZIPHttpGetIn></a>GetCityForecastByZIPHttpGetIn<br/>
**Parts:**<br/>
**Name**: ZIP<br />
**Type**: [string](#string)

<a id=GetCityForecastByZIPHttpGetOut></a>GetCityForecastByZIPHttpGetOut<br/>
**Parts:**<br/>
**Name**: Body<br />
**Type**: [ForecastReturn](#ForecastReturn)<br />

<a id=GetCityWeatherByZIPHttpGetIn></a>GetCityWeatherByZIPHttpGetIn<br/>
**Parts:**<br/>
**Name**: ZIP<br />
**Type**: [string](#string)

<a id=GetCityWeatherByZIPHttpGetOut></a>GetCityWeatherByZIPHttpGetOut<br/>
**Parts:**<br/>
**Name**: Body<br />
**Type**: [WeatherReturn](#WeatherReturn)<br />

<a id=GetWeatherInformationHttpPostIn></a>GetWeatherInformationHttpPostIn<br/>

<a id=GetWeatherInformationHttpPostOut></a>GetWeatherInformationHttpPostOut<br/>
**Parts:**<br/>
**Name**: Body<br />
**Type**: [ArrayOfWeatherDescription](#ArrayOfWeatherDescription)<br />

<a id=GetCityForecastByZIPHttpPostIn></a>GetCityForecastByZIPHttpPostIn<br/>
**Parts:**<br/>
**Name**: ZIP<br />
**Type**: [string](#string)

<a id=GetCityForecastByZIPHttpPostOut></a>GetCityForecastByZIPHttpPostOut<br/>
**Parts:**<br/>
**Name**: Body<br />
**Type**: [ForecastReturn](#ForecastReturn)<br />

<a id=GetCityWeatherByZIPHttpPostIn></a>GetCityWeatherByZIPHttpPostIn<br/>
**Parts:**<br/>
**Name**: ZIP<br />
**Type**: [string](#string)

<a id=GetCityWeatherByZIPHttpPostOut></a>GetCityWeatherByZIPHttpPostOut<br/>
**Parts:**<br/>
**Name**: Body<br />
**Type**: [WeatherReturn](#WeatherReturn)<br />


Datatypes
---------
<a id=ArrayOfForecast></a>ArrayOfForecast
<br />- **Name**: Forecast
<br />**Type**: Forecast
<br />**Cardinality**: 0..unbounded
<br /><a id=GetCityForecastByZIPResponse></a>GetCityForecastByZIPResponse
<br />- **Name**: GetCityForecastByZIPResult
<br />**Type**: ForecastReturn
<br />**Cardinality**: 0..1
<br /><a id=GetWeatherInformation></a>GetWeatherInformation
<br /><a id=GetWeatherInformationResponse></a>GetWeatherInformationResponse
<br />- **Name**: GetWeatherInformationResult
<br />**Type**: ArrayOfWeatherDescription
<br />**Cardinality**: 0..1
<br /><a id=temp></a>temp
<br />- **Name**: MorningLow
<br />**Type**: string
<br />**Cardinality**: 0..1
- **Name**: DaytimeHigh
<br />**Type**: string
<br />**Cardinality**: 0..1
<br /><a id=WeatherDescription></a>WeatherDescription
<br />- **Name**: WeatherID
<br />**Type**: short
<br />**Cardinality**: 1..1
- **Name**: Description
<br />**Type**: string
<br />**Cardinality**: 0..1
- **Name**: PictureURL
<br />**Type**: string
<br />**Cardinality**: 0..1
<br /><a id=GetCityWeatherByZIP></a>GetCityWeatherByZIP
<br />- **Name**: ZIP
<br />**Type**: string
<br />**Cardinality**: 0..1
<br /><a id=Forecast></a>Forecast
<br />- **Name**: Date
<br />**Type**: dateTime
<br />**Cardinality**: 1..1
- **Name**: WeatherID
<br />**Type**: short
<br />**Cardinality**: 1..1
- **Name**: Desciption
<br />**Type**: string
<br />**Cardinality**: 0..1
- **Name**: Temperatures
<br />**Type**: temp
<br />**Cardinality**: 1..1
- **Name**: ProbabilityOfPrecipiation
<br />**Type**: POP
<br />**Cardinality**: 1..1
<br /><a id=GetCityForecastByZIP></a>GetCityForecastByZIP
<br />- **Name**: ZIP
<br />**Type**: string
<br />**Cardinality**: 0..1
<br /><a id=short></a>short
<br /><a id=GetCityWeatherByZIPResponse></a>GetCityWeatherByZIPResponse
<br />- **Name**: GetCityWeatherByZIPResult
<br />**Type**: WeatherReturn
<br />**Cardinality**: 1..1
<br /><a id=ArrayOfWeatherDescription></a>ArrayOfWeatherDescription
<br />- **Name**: WeatherDescription
<br />**Type**: WeatherDescription
<br />**Cardinality**: 0..unbounded
<br /><a id=ForecastReturn></a>ForecastReturn
<br />- **Name**: Success
<br />**Type**: boolean
<br />**Cardinality**: 1..1
- **Name**: ResponseText
<br />**Type**: string
<br />**Cardinality**: 0..1
- **Name**: State
<br />**Type**: string
<br />**Cardinality**: 0..1
- **Name**: City
<br />**Type**: string
<br />**Cardinality**: 0..1
- **Name**: WeatherStationCity
<br />**Type**: string
<br />**Cardinality**: 0..1
- **Name**: ForecastResult
<br />**Type**: ArrayOfForecast
<br />**Cardinality**: 0..1
<br /><a id=boolean></a>boolean
<br /><a id=dateTime></a>dateTime
<br /><a id=POP></a>POP
<br />- **Name**: Nighttime
<br />**Type**: string
<br />**Cardinality**: 0..1
- **Name**: Daytime
<br />**Type**: string
<br />**Cardinality**: 0..1
<br /><a id=WeatherReturn></a>WeatherReturn
<br />- **Name**: Success
<br />**Type**: boolean
<br />**Cardinality**: 1..1
- **Name**: ResponseText
<br />**Type**: string
<br />**Cardinality**: 0..1
- **Name**: State
<br />**Type**: string
<br />**Cardinality**: 0..1
- **Name**: City
<br />**Type**: string
<br />**Cardinality**: 0..1
- **Name**: WeatherStationCity
<br />**Type**: string
<br />**Cardinality**: 0..1
- **Name**: WeatherID
<br />**Type**: short
<br />**Cardinality**: 1..1
- **Name**: Description
<br />**Type**: string
<br />**Cardinality**: 0..1
- **Name**: Temperature
<br />**Type**: string
<br />**Cardinality**: 0..1
- **Name**: RelativeHumidity
<br />**Type**: string
<br />**Cardinality**: 0..1
- **Name**: Wind
<br />**Type**: string
<br />**Cardinality**: 0..1
- **Name**: Pressure
<br />**Type**: string
<br />**Cardinality**: 0..1
- **Name**: Visibility
<br />**Type**: string
<br />**Cardinality**: 0..1
- **Name**: WindChill
<br />**Type**: string
<br />**Cardinality**: 0..1
- **Name**: Remarks
<br />**Type**: string
<br />**Cardinality**: 0..1
<br /><a id=string></a>string
<br />