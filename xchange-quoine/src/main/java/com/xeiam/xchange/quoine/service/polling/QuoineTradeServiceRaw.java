package com.xeiam.xchange.quoine.service.polling;

import java.io.IOException;
import java.math.BigDecimal;

import si.mazi.rescu.HttpStatusIOException;

import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.quoine.QuoineUtils;
import com.xeiam.xchange.quoine.dto.trade.QuoineNewOrderRequest;
import com.xeiam.xchange.quoine.dto.trade.QuoinePlaceOrderResponse;

/**
 * @author gnandiga
 */
public class QuoineTradeServiceRaw extends QuoineBasePollingService {

  /**
   * @param exchange
   */
  public QuoineTradeServiceRaw(Exchange exchange) {

    super(exchange);
  }

  //  public BitstampOrder[] getBitstampOpenOrders() throws IOException {
  //
  //    return bitstampAuthenticated.getOpenOrders(exchange.getExchangeSpecification().getApiKey(), signatureCreator, exchange.getNonceFactory());
  //  }

  public QuoinePlaceOrderResponse placeLimitOrder(CurrencyPair currencyPair, String type, BigDecimal tradableAmount, BigDecimal price)
      throws IOException {

    QuoineNewOrderRequest quoineNewOrderRequest = new QuoineNewOrderRequest("limit", QuoineUtils.toPairString(currencyPair), type, tradableAmount,
        price);
    try {
      return quoine.placeOrder(device, userID, userToken, quoineNewOrderRequest);
    } catch (HttpStatusIOException e) {
      throw handleHttpError(e);
    }
  }

  //  public BitstampOrder buyBitStampOrder(BigDecimal tradableAmount, BigDecimal price) throws IOException {
  //
  //    return bitstampAuthenticated.buy(exchange.getExchangeSpecification().getApiKey(), signatureCreator, exchange.getNonceFactory(), tradableAmount,
  //        price);
  //  }
  //
  //  public boolean cancelBitstampOrder(int orderId) throws IOException {
  //
  //    return bitstampAuthenticated.cancelOrder(exchange.getExchangeSpecification().getApiKey(), signatureCreator, exchange.getNonceFactory(), orderId);
  //  }
  //
  //  public BitstampUserTransaction[] getBitstampUserTransactions(Long numberOfTransactions) throws IOException {
  //
  //    return bitstampAuthenticated.getUserTransactions(exchange.getExchangeSpecification().getApiKey(), signatureCreator, exchange.getNonceFactory(),
  //        numberOfTransactions);
  //  }
  //
  //  public BitstampUserTransaction[] getBitstampUserTransactions(Long numberOfTransactions, Long offset, String sort) throws IOException {
  //
  //    return bitstampAuthenticated.getUserTransactions(exchange.getExchangeSpecification().getApiKey(), signatureCreator, exchange.getNonceFactory(),
  //        numberOfTransactions, offset, sort);
  //  }

}
