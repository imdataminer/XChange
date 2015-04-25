package com.xeiam.xchange.quoine;

import si.mazi.rescu.SynchronizedValueFactory;

import com.xeiam.xchange.BaseExchange;
import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.ExchangeSpecification;
import com.xeiam.xchange.quoine.service.polling.QuoineAccountService;
import com.xeiam.xchange.quoine.service.polling.QuoineMarketDataService;
import com.xeiam.xchange.quoine.service.polling.QuoineTradeService;

public class QuoineExchange extends BaseExchange implements Exchange {

  public static final String KEY_USER_ID = "KEY_USER_ID";
  public static final String KEY_DEVICE_NAME = "KEY_DEVICE_NAME";
  public static final String KEY_USER_TOKEN = "KEY_USER_TOKEN";

  @Override
  public void applySpecification(ExchangeSpecification exchangeSpecification) {

    super.applySpecification(exchangeSpecification);

    this.pollingMarketDataService = new QuoineMarketDataService(this);
    this.pollingAccountService = new QuoineAccountService(this);
    this.pollingTradeService = new QuoineTradeService(this);
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {

    ExchangeSpecification exchangeSpecification = new ExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setSslUri("https://quoine-stag4.herokuapp.com");
    exchangeSpecification.setExchangeName("Quoine");
    return exchangeSpecification;
  }

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {

    // not used by this exchange
    return null;
  }
}
