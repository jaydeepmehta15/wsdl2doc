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
========
<a id=GetWeatherInformationSoapIn></a>GetWeatherInformationSoapIn
**Name**: GetWeatherInformation
<a id=GetWeatherInformationSoapOut></a>GetWeatherInformationSoapOut
**Name**: GetWeatherInformationResult
**Cardinality**: 0..1
<a id=GetCityForecastByZIPSoapIn></a>GetCityForecastByZIPSoapIn
**Name**: ZIP
**Cardinality**: 0..1
**Type**: string
<a id=GetCityForecastByZIPSoapOut></a>GetCityForecastByZIPSoapOut
**Name**: GetCityForecastByZIPResult
**Cardinality**: 0..1
<a id=GetCityWeatherByZIPSoapIn></a>GetCityWeatherByZIPSoapIn
**Name**: ZIP
**Cardinality**: 0..1
**Type**: string
<a id=GetCityWeatherByZIPSoapOut></a>GetCityWeatherByZIPSoapOut
**Name**: GetCityWeatherByZIPResult
**Cardinality**: 1..1
<a id=GetWeatherInformationHttpGetOut></a>GetWeatherInformationHttpGetOut
**Name**: WeatherDescription
**Cardinality**: 0..unbounded
<a id=GetCityForecastByZIPHttpGetOut></a>GetCityForecastByZIPHttpGetOut
**Name**: Success
**Cardinality**: 1..1
**Type**: boolean
**Name**: ResponseText
**Cardinality**: 0..1
**Type**: string
**Name**: State
**Cardinality**: 0..1
**Type**: string
**Name**: City
**Cardinality**: 0..1
**Type**: string
**Name**: WeatherStationCity
**Cardinality**: 0..1
**Type**: string
**Name**: ForecastResult
**Cardinality**: 0..1
<a id=GetCityWeatherByZIPHttpGetOut></a>GetCityWeatherByZIPHttpGetOut
**Name**: Success
**Cardinality**: 1..1
**Type**: boolean
**Name**: ResponseText
**Cardinality**: 0..1
**Type**: string
**Name**: State
**Cardinality**: 0..1
**Type**: string
**Name**: City
**Cardinality**: 0..1
**Type**: string
**Name**: WeatherStationCity
**Cardinality**: 0..1
**Type**: string
**Name**: WeatherID
**Cardinality**: 1..1
**Type**: short
**Name**: Description
**Cardinality**: 0..1
**Type**: string
**Name**: Temperature
**Cardinality**: 0..1
**Type**: string
**Name**: RelativeHumidity
**Cardinality**: 0..1
**Type**: string
**Name**: Wind
**Cardinality**: 0..1
**Type**: string
**Name**: Pressure
**Cardinality**: 0..1
**Type**: string
**Name**: Visibility
**Cardinality**: 0..1
**Type**: string
**Name**: WindChill
**Cardinality**: 0..1
**Type**: string
**Name**: Remarks
**Cardinality**: 0..1
**Type**: string
<a id=GetWeatherInformationHttpPostOut></a>GetWeatherInformationHttpPostOut
**Name**: WeatherDescription
**Cardinality**: 0..unbounded
<a id=GetCityForecastByZIPHttpPostOut></a>GetCityForecastByZIPHttpPostOut
**Name**: Success
**Cardinality**: 1..1
**Type**: boolean
**Name**: ResponseText
**Cardinality**: 0..1
**Type**: string
**Name**: State
**Cardinality**: 0..1
**Type**: string
**Name**: City
**Cardinality**: 0..1
**Type**: string
**Name**: WeatherStationCity
**Cardinality**: 0..1
**Type**: string
**Name**: ForecastResult
**Cardinality**: 0..1
<a id=GetCityWeatherByZIPHttpPostOut></a>GetCityWeatherByZIPHttpPostOut
**Name**: Success
**Cardinality**: 1..1
**Type**: boolean
**Name**: ResponseText
**Cardinality**: 0..1
**Type**: string
**Name**: State
**Cardinality**: 0..1
**Type**: string
**Name**: City
**Cardinality**: 0..1
**Type**: string
**Name**: WeatherStationCity
**Cardinality**: 0..1
**Type**: string
**Name**: WeatherID
**Cardinality**: 1..1
**Type**: short
**Name**: Description
**Cardinality**: 0..1
**Type**: string
**Name**: Temperature
**Cardinality**: 0..1
**Type**: string
**Name**: RelativeHumidity
**Cardinality**: 0..1
**Type**: string
**Name**: Wind
**Cardinality**: 0..1
**Type**: string
**Name**: Pressure
**Cardinality**: 0..1
**Type**: string
**Name**: Visibility
**Cardinality**: 0..1
**Type**: string
**Name**: WindChill
**Cardinality**: 0..1
**Type**: string
**Name**: Remarks
**Cardinality**: 0..1
**Type**: string
