package vttp.batch4.csf.ecommerce.controllers;

import java.io.StringReader;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
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

    JsonArray cart = json.getJsonObject("cart").getJsonArray("lineItems");

    System.out.println("json" + json);

    List<LineItem> item = new LinkedList<>();
    for(JsonValue value: cart) {
        JsonObject obj =value.asJsonObject();
        LineItem i = new LineItem();
        i.setProductId(obj.getString("prodId"));
        i.setName(obj.getString("name"));
        i.setPrice(Float.parseFloat(obj.get("price").toString()));
        i.setQuantity(obj.getInt("quantity"));
        item.add(i);
    }

    System.out.println("item" + item);

    Cart c = new Cart();
    c.setLineItems(item);

    Order order = new Order();
    
    order.getOrderId();
    order.setName(json.getString("name"));
    order.setAddress(json.getString("address"));
    order.setPriority(json.getBoolean("priority"));
    order.setDate(new Date());
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
