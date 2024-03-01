import { Component, OnInit, inject } from '@angular/core';
import { Form, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { LineItem } from '../models';
import { CartStore } from '../cart.store';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-confirm-checkout',
  templateUrl: './confirm-checkout.component.html',
  styleUrl: './confirm-checkout.component.css'
})
export class ConfirmCheckoutComponent implements OnInit{

  // TODO Task 3
  private fb = inject(FormBuilder)
  private cartSvc = inject(CartStore)
  private productSvc = inject(ProductService)

  itemsList$!: Observable<LineItem[]>
  
  form!:FormGroup

  ngOnInit(): void {
    this.form = this.createForm()

    this.itemsList$ = this.cartSvc.getLineItems
    
  }

  createForm(): FormGroup {
    return this.fb.group({
      name:this.fb.control<string>('', [Validators.required]),
      address: this.fb.control<string>('', [Validators.required, Validators.minLength(3)]),
      priority: this.fb.control<boolean>(false), //default to false
      comments: this.fb.control<string>('') //not mandatory
    })
  }

  //process form when user clicks submit
  process() {
    const checkout = this.form.value
    console.info(">>>checking out", checkout)

    this.productSvc.checkout(this.form.value)
      .then(resp => {
        console.info('resp:', resp)
        alert((resp as any).orderId)
    })

  }






}
