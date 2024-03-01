package vttp.batch4.csf.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp.batch4.csf.ecommerce.models.Order;
import vttp.batch4.csf.ecommerce.services.PurchaseOrderService;

@Controller
public class OrderController {

  @Autowired
  private PurchaseOrderService poSvc;

  // IMPORTANT: DO NOT MODIFY THIS METHOD.
  // If this method is changed, any assessment task relying on this method will
  // not be marked
  @PostMapping(path="/post", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> postOrder(@RequestPart String name, @RequestPart String address, @RequestPart boolean priority
  ,@RequestPart (required=false) String comments ) {

    // TODO Task 3
    Order order = new Order();
    order.getOrderId();
    order.setName(name);
    order.setAddress(address);
    order.setPriority(priority);
    order.setComments(comments);

    poSvc.createNewPurchaseOrder(order);

    JsonObject orderID = Json.createObjectBuilder()
        .add("orderid", order.getOrderId())
        .build();

	 return ResponseEntity.ok(orderID.toString());
  }
}
