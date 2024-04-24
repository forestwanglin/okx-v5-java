# okx-v5-java
[![GitHub version](https://img.shields.io/static/v1?label=version&message=v0.2.2024042401&color=blue)](https://github.com/forestwanglin/openai-java)
[![License](https://img.shields.io/static/v1?label=license&message=MIT&color=orange)](https://github.com/forestwanglin/okx-v5-java/blob/main/LICENSE)

OKX v5 SDK for JAVA. 

I am going to implement all apis on the official api document. [https://www.okx.com/docs-v5/en/#overview](https://www.okx.com/docs-v5/en/#overview)

## Update history
- [2024-04-20] Release the first version `0.1.2024042001` which contains websocket only, and contains below channels:
  - Account, Positions, Balance and position, Position risk warning and Account greeks on document [https://www.okx.com/docs-v5/zh/#trading-account-websocket](https://www.okx.com/docs-v5/zh/#trading-account-websocket).
  - Instruments and Open interest on document [https://www.okx.com/docs-v5/zh/#public-data-websocket](https://www.okx.com/docs-v5/zh/#public-data-websocket).

- [2024-04-24] Add more api for websocket only:
  - [Trading Account](https://www.okx.com/docs-v5/zh/#trading-account-websocket)
    - Account
    - Positions
    - Balance and position
    - Position risk warning
    - Account greeks
  - [Public Data](https://www.okx.com/docs-v5/en/#public-data)
    - Instruments
    - Open interest
    - Funding rate
    - Price limit
    - Option summary
    - Estimated delivery/exercise price
    - Mark price
    - Index tickers
    - Mark price candlesticks
    - Index candlesticks
    - Liquidation orders
    - ADL warning
    - Economic calendar channel
  - [Funding Account](https://www.okx.com/docs-v5/en/#funding-account)
    - Deposit info
    - Withdrawal info

## How to use

### Maven

```xml

<dependency>
    <groupId>xyz.felh</groupId>
    <artifactId>okx-v5-java</artifactId>
    <version>0.2.2024042401</version>
</dependency>
```

### Gradle

```yaml
implementation group: 'xyz.felh', name: 'okx-v5-java', version: '0.2.2024042401'
```

### sbt

```javascript
libraryDependencies += "xyz.felh" % "okx-v5-java" % "0.2.2024042401"
```


## License

Published under the MIT License (https://github.com/forestwanglin/okx-v5-java/blob/main/LICENSE)