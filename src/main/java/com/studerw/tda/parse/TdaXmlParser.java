package com.studerw.tda.parse;

import com.studerw.tda.model.BalancesAndPositions;
import com.studerw.tda.model.trade.CancelOrder;
import com.studerw.tda.model.LastOrderStatus;
import com.studerw.tda.model.Logout;
import com.studerw.tda.model.MarketSnapshot;
import com.studerw.tda.model.OptionChain;
import com.studerw.tda.model.trade.EquityTrade;
import com.studerw.tda.model.trade.OptionTrade;
import com.studerw.tda.model.OrderStatus;
import com.studerw.tda.model.QuoteResponse;
import com.studerw.tda.model.SymbolLookup;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is responsible for parsing XML (non-binary) responses from the TDA Server API and
 * converts them to model POJOs. It assumes all XML is well-formed UTF-8.
 *
 * It uses JAXB to convert XML to pojos. None of these methods throw checked exceptions, but if an
 * error occurs, an unchecked IllegalStateException is thrown with the wrapped JAXB or other error.
 */
public class TdaXmlParser {

  private static final Logger LOGGER = LoggerFactory.getLogger(TdaXmlParser.class);
  private static final String FAIL = "FAIL";

  public TdaXmlParser() {
  }

//    protected <T> parseXml(T type, String xml){
//        try (InputStream in = IOUtils.toInputStream(xml, StandardCharsets.UTF_8)){
//            LOGGER.debug("unmarshalling xml to pojo of type: {}", type.getClass().getName());
//            JAXBContext context = JAXBContext.newInstance(type.getClass());
//            Unmarshaller um = context.createUnmarshaller();
//            final T obj = (T)um.unmarshal(in);
//            login.setOriginalXml(xml);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new IllegalStateException("Error parsing login");
//        }
//    }


  public OrderStatus parseOrderStatus(String xml) {
    try (InputStream in = IOUtils.toInputStream(xml, StandardCharsets.UTF_8)) {
      JAXBContext context = JAXBContext.newInstance(OrderStatus.class);
      Unmarshaller um = context.createUnmarshaller();
      OrderStatus orderStatus = (OrderStatus) um.unmarshal(in);
      orderStatus.setOriginalXml(xml);
      if (orderStatus.getResult().equalsIgnoreCase(FAIL)) {
        orderStatus.setTdaError(true);
      }
      return orderStatus;
    } catch (Exception e) {
      e.printStackTrace();
      throw new IllegalStateException("Error parsing Order Status");
    }

  }

  public BalancesAndPositions parseBalancesAndPositions(String xml) {
    try (InputStream in = IOUtils.toInputStream(xml, StandardCharsets.UTF_8)) {
      JAXBContext context = JAXBContext.newInstance(BalancesAndPositions.class);
      Unmarshaller um = context.createUnmarshaller();
      BalancesAndPositions balances = (BalancesAndPositions) um.unmarshal(in);
      balances.setOriginalXml(xml);
      if (balances.getResult().equalsIgnoreCase(FAIL)) {
        balances.setTdaError(true);
      }
      return balances;
    } catch (Exception e) {
      e.printStackTrace();
      throw new IllegalStateException("Error parsing balances");
    }
  }

  public OptionTrade parseOptionTrade(String xml) {
    try (InputStream in = IOUtils.toInputStream(xml, StandardCharsets.UTF_8)) {
      JAXBContext context = JAXBContext.newInstance(OptionTrade.class);
      Unmarshaller um = context.createUnmarshaller();
      OptionTrade optionTrade = (OptionTrade) um.unmarshal(in);
      optionTrade.setOriginalXml(xml);
      if (optionTrade.getResult().equalsIgnoreCase(FAIL)) {
        optionTrade.setTdaError(true);
      }
      return optionTrade;
    } catch (Exception e) {
      e.printStackTrace();
      throw new IllegalStateException("Error parsing option trade response");
    }
  }

  public OptionChain parseOptionChain(String xml) {
    try (InputStream in = IOUtils.toInputStream(xml, StandardCharsets.UTF_8)) {
      JAXBContext context = JAXBContext.newInstance(OptionChain.class);
      Unmarshaller um = context.createUnmarshaller();
      OptionChain optionChain = (OptionChain) um.unmarshal(in);
      optionChain.setOriginalXml(xml);
      if (optionChain.getResult().equalsIgnoreCase(FAIL)) {
        optionChain.setTdaError(true);
      }
      return optionChain;
    } catch (Exception e) {
      e.printStackTrace();
      throw new IllegalStateException("Error parsing optionChain");
    }
  }

  public Logout parseLogout(String xml) {
    try (InputStream in = IOUtils.toInputStream(xml, StandardCharsets.UTF_8)) {
      JAXBContext context = JAXBContext.newInstance(Logout.class);
      Unmarshaller um = context.createUnmarshaller();
      return (Logout) um.unmarshal(in);
    } catch (Exception e) {
      e.printStackTrace();
      throw new IllegalStateException("Error parsing Logout");
    }
  }


  public SymbolLookup parseSymbolLookup(String xml) {
    try (InputStream in = IOUtils.toInputStream(xml, StandardCharsets.UTF_8)) {
      JAXBContext context = JAXBContext.newInstance(SymbolLookup.class);
      Unmarshaller um = context.createUnmarshaller();
      final SymbolLookup result = (SymbolLookup) um.unmarshal(in);
      result.setOriginalXml(xml);
      if (result.getResultStr().equalsIgnoreCase(FAIL)) {
        result.setTdaError(true);
      }
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      throw new IllegalStateException("Error parsing symbolLookupResponse");
    }
  }

  public CancelOrder parseCancelOrder(String xml) {
    try (InputStream in = IOUtils.toInputStream(xml, StandardCharsets.UTF_8)) {
      JAXBContext context = JAXBContext.newInstance(CancelOrder.class);
      Unmarshaller um = context.createUnmarshaller();
      final CancelOrder result = (CancelOrder) um.unmarshal(in);
      result.setOriginalXml(xml);
      if (result.getResult().equalsIgnoreCase(FAIL)) {
        result.setTdaError(true);
      }
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      throw new IllegalStateException("Error parsing CancelOrder");
    }
  }

  public QuoteResponse parseQuoteResponse(String xml) {
    try (InputStream in = IOUtils.toInputStream(xml, StandardCharsets.UTF_8)) {
      JAXBContext context = JAXBContext.newInstance(QuoteResponse.class);
      Unmarshaller um = context.createUnmarshaller();
      QuoteResponse quoteResponse = (QuoteResponse) um.unmarshal(in);
      quoteResponse.setOriginalXml(xml);
      if (quoteResponse.getResultStr().equalsIgnoreCase(FAIL)) {
        quoteResponse.setTdaError(true);
      }
      return quoteResponse;
    } catch (Exception e) {
      e.printStackTrace();
      throw new IllegalStateException("Error parsing QuoteResponse");
    }
  }

  public LastOrderStatus parseLastOrderStatus(String xml) {
    try (InputStream in = IOUtils.toInputStream(xml, StandardCharsets.UTF_8)) {
      JAXBContext context = JAXBContext.newInstance(LastOrderStatus.class);
      Unmarshaller um = context.createUnmarshaller();
      LastOrderStatus lastOrderStatus = (LastOrderStatus) um.unmarshal(in);
      lastOrderStatus.setOriginalXml(xml);
      if (lastOrderStatus.getResult().equalsIgnoreCase(FAIL)) {
        lastOrderStatus.setTdaError(true);
      }
      return lastOrderStatus;
    } catch (Exception e) {
      e.printStackTrace();
      throw new IllegalStateException("Error parsing QuoteResponse");
    }
  }

  public MarketSnapshot parseMarketSnapshot(String xml) {
    try (InputStream in = IOUtils.toInputStream(xml, StandardCharsets.UTF_8)) {
      JAXBContext context = JAXBContext.newInstance(MarketSnapshot.class);
      Unmarshaller um = context.createUnmarshaller();
      MarketSnapshot marketSnapshot = (MarketSnapshot) um.unmarshal(in);
      marketSnapshot.setOriginalXml(xml);
      if (marketSnapshot.getResult().equalsIgnoreCase(FAIL)) {
        marketSnapshot.setTdaError(true);
      }
      return marketSnapshot;
    } catch (Exception e) {
      e.printStackTrace();
      throw new IllegalStateException("Error parsing QuoteResponse");
    }
  }

  public EquityTrade parseEquityTrade(String xml) {
    try (InputStream in = IOUtils.toInputStream(xml, StandardCharsets.UTF_8)) {
      JAXBContext context = JAXBContext.newInstance(EquityTrade.class);
      Unmarshaller um = context.createUnmarshaller();
      EquityTrade equityTrade = (EquityTrade) um.unmarshal(in);
      equityTrade.setOriginalXml(xml);
      if (equityTrade.getResult().equalsIgnoreCase(FAIL)) {
        equityTrade.setTdaError(true);
      }
      return equityTrade;
    } catch (Exception e) {
      e.printStackTrace();
      throw new IllegalStateException("Error parsing QuoteResponse");
    }
  }
}
