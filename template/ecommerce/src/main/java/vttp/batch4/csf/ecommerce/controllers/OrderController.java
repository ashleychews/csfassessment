package vttp.batch4.csf.ecommerce.controllers;

import java.io.StringReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.batch4.csf.ecommerce.models.Cart;
import vttp.batch4.csf.ecommerce.models.LineItem;
import vttp.batch4.csf.ecommerce.models.Order;
import vttp.batch4.csf.ecommerce.services.PurchaseOrderService;

@Controller
@RequestMapping(path="/api")
public class OrderController {

  @Autowired
  private PurchaseOrderService poSvc;

  // IMPORTANT: DO NOT MODIFY THIS METHOD.
  // If this method is changed, any assessment task relying on this method will
  // not be marked
  @PostMapping(path="/order", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<String> postOrder(@RequestBody String payload ) {

    // TODO Task 3
    JsonReader reader = Json.createReader(new StringReader(payload));
    JsonObject json = reader.readObject();

    System.out.println("json" + json);

    LineItem i = new LineItem();
    i.setProductId(json.getString("productId"));
    i.setName(json.getString("name"));
    i.setPrice(json.getInt("price"));
    i.setQuantity(json.getInt("quantity"));

    Cart c = new Cart();
    c.getLineItems();

    Order order = new Order();
    order.getOrderId();
    order.setName(json.getString("name"));
    order.setAddress(json.getString("address"));
    order.setPriority(json.getBoolean("priority"));
    String com = json.getString("comments");
    if (com!=null) {
      order.setComments(com);
    }
    order.setCart(c);

    poSvc.createNewPurchaseOrder(order);

    JsonObject orderId = Json.createObjectBuilder()
        .add("orderid", order.getOrderId())
        .build();
    
    if (orderId.toString()!=null) {
      return ResponseEntity.ok(orderId.toString());
    }
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
      .build();
  }
}
