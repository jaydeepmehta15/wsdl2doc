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
**Type**: Forecast
**Cardinality**: 0..unbounded


<a id=GetCityForecastByZIPResponse></a>GetCityForecastByZIPResponse
- **Name**: GetCityForecastByZIPResult
**Type**: ForecastReturn
**Cardinality**: 0..1


<a id=GetWeatherInformation></a>GetWeatherInformation
<br /><br />
<a id=GetWeatherInformationResponse></a>GetWeatherInformationResponse
- **Name**: GetWeatherInformationResult
**Type**: ArrayOfWeatherDescription
**Cardinality**: 0..1


<a id=temp></a>temp
- **Name**: MorningLow
**Type**: string
**Cardinality**: 0..1

- **Name**: DaytimeHigh
**Type**: string
**Cardinality**: 0..1


<a id=WeatherDescription></a>WeatherDescription
- **Name**: WeatherID
**Type**: short
**Cardinality**: 1..1

- **Name**: Description
**Type**: string
**Cardinality**: 0..1

- **Name**: PictureURL
**Type**: string
**Cardinality**: 0..1


<a id=GetCityWeatherByZIP></a>GetCityWeatherByZIP
- **Name**: ZIP
**Type**: string
**Cardinality**: 0..1


<a id=Forecast></a>Forecast
- **Name**: Date
**Type**: dateTime
**Cardinality**: 1..1

- **Name**: WeatherID
**Type**: short
**Cardinality**: 1..1

- **Name**: Desciption
**Type**: string
**Cardinality**: 0..1

- **Name**: Temperatures
**Type**: temp
**Cardinality**: 1..1

- **Name**: ProbabilityOfPrecipiation
**Type**: POP
**Cardinality**: 1..1


<a id=GetCityForecastByZIP></a>GetCityForecastByZIP
- **Name**: ZIP
**Type**: string
**Cardinality**: 0..1


<a id=short></a>short
<br /><br />
<a id=GetCityWeatherByZIPResponse></a>GetCityWeatherByZIPResponse
- **Name**: GetCityWeatherByZIPResult
**Type**: WeatherReturn
**Cardinality**: 1..1


<a id=ArrayOfWeatherDescription></a>ArrayOfWeatherDescription
- **Name**: WeatherDescription
**Type**: WeatherDescription
**Cardinality**: 0..unbounded


<a id=ForecastReturn></a>ForecastReturn
- **Name**: Success
**Type**: boolean
**Cardinality**: 1..1

- **Name**: ResponseText
**Type**: string
**Cardinality**: 0..1

- **Name**: State
**Type**: string
**Cardinality**: 0..1

- **Name**: City
**Type**: string
**Cardinality**: 0..1

- **Name**: WeatherStationCity
**Type**: string
**Cardinality**: 0..1

- **Name**: ForecastResult
**Type**: ArrayOfForecast
**Cardinality**: 0..1


<a id=boolean></a>boolean
<br /><br />
<a id=dateTime></a>dateTime
<br /><br />
<a id=POP></a>POP
- **Name**: Nighttime
**Type**: string
**Cardinality**: 0..1

- **Name**: Daytime
**Type**: string
**Cardinality**: 0..1


<a id=WeatherReturn></a>WeatherReturn
- **Name**: Success
**Type**: boolean
**Cardinality**: 1..1

- **Name**: ResponseText
**Type**: string
**Cardinality**: 0..1

- **Name**: State
**Type**: string
**Cardinality**: 0..1

- **Name**: City
**Type**: string
**Cardinality**: 0..1

- **Name**: WeatherStationCity
**Type**: string
**Cardinality**: 0..1

- **Name**: WeatherID
**Type**: short
**Cardinality**: 1..1

- **Name**: Description
**Type**: string
**Cardinality**: 0..1

- **Name**: Temperature
**Type**: string
**Cardinality**: 0..1

- **Name**: RelativeHumidity
**Type**: string
**Cardinality**: 0..1

- **Name**: Wind
**Type**: string
**Cardinality**: 0..1

- **Name**: Pressure
**Type**: string
**Cardinality**: 0..1

- **Name**: Visibility
**Type**: string
**Cardinality**: 0..1

- **Name**: WindChill
**Type**: string
**Cardinality**: 0..1

- **Name**: Remarks
**Type**: string
**Cardinality**: 0..1


<a id=string></a>string
<br /><br />
