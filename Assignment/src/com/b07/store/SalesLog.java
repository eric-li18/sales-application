package com.b07.store;

import com.b07.inventory.Item;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * Interface for a sales log
 *
 * @author Harry
 */
public interface SalesLog {

  /**
   * Method for getting the sales log
   */
  public List<Sale> getLog();

  /**
   * Method for updating the sales log
   *
   * @param sale
   */
  public void updateLog(Sale sale);

  public HashMap<Item, Integer> getTotalItemMap();

  /**
   * Method to return the total dollars in sales
   *
   * @return total dollars in sales
   */
  public BigDecimal getTotalSales();
}