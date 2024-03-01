package vttp.batch4.csf.ecommerce.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp.batch4.csf.ecommerce.models.Order;

@Repository
public class PurchaseOrderRepository {

  public static final String SQL_INSERT_ORDER = """
  
    insert into order (id, date, name, address, priority, comments, cart)
    values (? , ?, ?, ?, ?)
  """;

  @Autowired
  private JdbcTemplate template;

  // IMPORTANT: DO NOT MODIFY THIS METHOD.
  // If this method is changed, any assessment task relying on this method will
  // not be marked
  // You may only add Exception to the method's signature
  public void create(Order order) {
    // TODO Task 3
    template.update(SQL_INSERT_ORDER,
      order.getOrderId(),
      order.getDate(),
      order.getName(),
      order.getAddress(),
      order.getPriority(),
      order.getComments(),
      order.getCart());

  }
}
