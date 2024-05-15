# okx-v5-java
[![GitHub version](https://img.shields.io/static/v1?label=version&message=v0.4.2024051502&color=blue)](https://github.com/forestwanglin/okx-v5-java)
[![License](https://img.shields.io/static/v1?label=license&message=MIT&color=orange)](https://github.com/forestwanglin/okx-v5-java/blob/main/LICENSE)

OKX v5 SDK for JAVA. 

I am going to implement all APIs on the [official api document](https://www.okx.com/docs-v5/en/#overview).

## SDK API STATUS
- ### Rest API
  - [Trading Account](https://www.okx.com/docs-v5/en/#trading-account-rest-api)
    - [x] [Get Balance](https://www.okx.com/docs-v5/en/#trading-account-rest-api-get-balance)
    - [ ] [Get positions](https://www.okx.com/docs-v5/en/#trading-account-rest-api-get-positions)
    - [ ] [Get positions history](https://www.okx.com/docs-v5/en/#trading-account-rest-api-get-positions-history)
    - [ ] [Get account and position risk](https://www.okx.com/docs-v5/en/#trading-account-rest-api-get-account-and-position-risk)
    - [ ] [Get bills details (last 7 days)](https://www.okx.com/docs-v5/en/#trading-account-rest-api-get-bills-details-last-7-days)
    - [ ] [Get bills details (last 3 months)](https://www.okx.com/docs-v5/en/#trading-account-rest-api-get-bills-details-last-3-months)
    - [ ] [Get account configuration](https://www.okx.com/docs-v5/en/#trading-account-rest-api-get-account-configuration)
    - [ ] [Set position mode](https://www.okx.com/docs-v5/en/#trading-account-rest-api-set-position-mode)
    - [ ] [Set leverage](https://www.okx.com/docs-v5/en/#trading-account-rest-api-set-leverage)
    - [ ] [Get maximum buy/sell amount or open amount](https://www.okx.com/docs-v5/en/#trading-account-rest-api-get-maximum-buy-sell-amount-or-open-amount)
    - [ ] ...
  - [Order Book Trading](https://www.okx.com/docs-v5/en/#order-book-trading)
    - [Trade](https://www.okx.com/docs-v5/en/#order-book-trading-trade)
      - [x] [POST / Place order](https://www.okx.com/docs-v5/en/#order-book-trading-trade-post-place-order)
      - [ ] [POST / Place multiple orders](https://www.okx.com/docs-v5/en/#order-book-trading-trade-post-place-multiple-orders)
      - [x] [POST / Cancel order](https://www.okx.com/docs-v5/en/#order-book-trading-trade-post-cancel-order)
      - [ ] [POST / Cancel multiple orders](https://www.okx.com/docs-v5/en/#order-book-trading-trade-post-cancel-multiple-orders)
      - [ ] [POST / Amend order](https://www.okx.com/docs-v5/en/#order-book-trading-trade-post-amend-order)
      - [ ] [POST / Amend multiple orders](https://www.okx.com/docs-v5/en/#order-book-trading-trade-post-amend-multiple-orders)
      - [ ] [POST / Close positions](https://www.okx.com/docs-v5/en/#order-book-trading-trade-post-close-positions)
      - [ ] ...
- ### Websocket API
  - [Trading Account](https://www.okx.com/docs-v5/zh/#trading-account-websocket)
    - [x] [Account channel](https://www.okx.com/docs-v5/en/#trading-account-websocket-account-channel)
    - [x] [Positions channel](https://www.okx.com/docs-v5/en/#trading-account-websocket-positions-channel)
    - [x] [Balance and position channel](https://www.okx.com/docs-v5/en/#trading-account-websocket-balance-and-position-channel)
    - [x] [Position risk warning](https://www.okx.com/docs-v5/en/#trading-account-websocket-position-risk-warning)
    - [x] [Account greeks channel](https://www.okx.com/docs-v5/en/#trading-account-websocket-account-greeks-channel)
  - [Order Book Trading](https://www.okx.com/docs-v5/en/#order-book-trading)
    - [Trade](https://www.okx.com/docs-v5/en/#order-book-trading-trade)
      - [x] [WS / Order channel](https://www.okx.com/docs-v5/en/#order-book-trading-trade-ws-order-channel)
      - [x] [WS / Place order](https://www.okx.com/docs-v5/en/#order-book-trading-trade-ws-place-order)
      - [x] [WS / Place multiple orders](https://www.okx.com/docs-v5/en/#order-book-trading-trade-ws-place-multiple-orders)
      - [x] [WS / Cancel order](https://www.okx.com/docs-v5/en/#order-book-trading-trade-ws-cancel-order)
      - [x] [WS / Cancel multiple orders](https://www.okx.com/docs-v5/en/#order-book-trading-trade-ws-cancel-multiple-orders)
      - [x] [WS / Amend order](https://www.okx.com/docs-v5/en/#order-book-trading-trade-ws-amend-order)
      - [x] [WS / Amend multiple orders](https://www.okx.com/docs-v5/en/#order-book-trading-trade-ws-amend-multiple-orders)
      - [x] [WS / Mass cancel order](https://www.okx.com/docs-v5/en/#order-book-trading-trade-ws-mass-cancel-order)
    - [Algo Trading](https://www.okx.com/docs-v5/en/#order-book-trading-algo-trading)
      - [x] [WS / Algo orders channel](https://www.okx.com/docs-v5/en/#order-book-trading-algo-trading-ws-algo-orders-channel)
      - [x] [WS / Advance algo orders channel](https://www.okx.com/docs-v5/en/#order-book-trading-algo-trading-ws-advance-algo-orders-channel)
    - [Grid Trading](https://www.okx.com/docs-v5/en/#order-book-trading-grid-trading)
      - [x] [WS / Spot grid algo orders channel](https://www.okx.com/docs-v5/en/#order-book-trading-grid-trading-ws-spot-grid-algo-orders-channel)
      - [x] [WS / Contract grid algo orders channel](https://www.okx.com/docs-v5/en/#order-book-trading-grid-trading-ws-contract-grid-algo-orders-channel)
      - [x] [WS / Grid positions channel](https://www.okx.com/docs-v5/en/#order-book-trading-grid-trading-ws-grid-positions-channel)
      - [x] [WS / Grid sub orders channel](https://www.okx.com/docs-v5/en/#order-book-trading-grid-trading-ws-grid-sub-orders-channel)
  - [Public Data](https://www.okx.com/docs-v5/en/#public-data-websocket)
    - [x] [Instruments channel](https://www.okx.com/docs-v5/en/#public-data-websocket-instruments-channel)
    - [x] [Open interest channel](https://www.okx.com/docs-v5/en/#public-data-websocket-open-interest-channel)
    - [x] [Funding rate channel](https://www.okx.com/docs-v5/en/#public-data-websocket-funding-rate-channel)
    - [x] [Price limit channel](https://www.okx.com/docs-v5/en/#public-data-websocket-price-limit-channel)
    - [x] [Option summary channel](https://www.okx.com/docs-v5/en/#public-data-websocket-option-summary-channel)
    - [x] [Estimated delivery/exercise price channel](https://www.okx.com/docs-v5/en/#public-data-websocket-estimated-delivery-exercise-price-channel)
    - [x] [Mark price channel](https://www.okx.com/docs-v5/en/#public-data-websocket-mark-price-channel)
    - [x] [Index tickers channel](https://www.okx.com/docs-v5/en/#public-data-websocket-index-tickers-channel)
    - [x] [Mark price candlesticks channel](https://www.okx.com/docs-v5/en/#public-data-websocket-mark-price-candlesticks-channel)
    - [x] [Index candlesticks channel](https://www.okx.com/docs-v5/en/#public-data-websocket-index-candlesticks-channel)
    - [x] [Liquidation orders channel](https://www.okx.com/docs-v5/en/#public-data-websocket-liquidation-orders-channel)
    - [x] [ADL warning channel](https://www.okx.com/docs-v5/en/#public-data-websocket-adl-warning-channel)
    - [x] [Economic calendar channel](https://www.okx.com/docs-v5/en/#public-data-websocket-economic-calendar-channel)
  - [Funding Account](https://www.okx.com/docs-v5/en/#funding-account-websocket)
    - [x] [Deposit info channel](https://www.okx.com/docs-v5/en/#funding-account-websocket-deposit-info-channel)
    - [x] [Withdrawal info channel](https://www.okx.com/docs-v5/en/#funding-account-websocket-withdrawal-info-channel)

## How to use

### Maven

```xml

<dependency>
    <groupId>xyz.felh</groupId>
    <artifactId>okx-v5-java</artifactId>
    <version>0.4.2024051502</version>
</dependency>
```

### Gradle

```yaml
implementation group: 'xyz.felh', name: 'okx-v5-java', version: '0.4.2024051502'
```

### sbt

```javascript
libraryDependencies += "xyz.felh" % "okx-v5-java" % "0.4.2024051502"
```

## Important

- #### Automatically login when reconnected websocket if it has been login to PRIVATE channel
- #### Automatically restore all subscribe channels when reconnected websocket


## License

Published under the MIT License (https://github.com/forestwanglin/okx-v5-java/blob/main/LICENSE)