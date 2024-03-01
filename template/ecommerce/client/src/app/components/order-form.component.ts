import { Component, ElementRef, Input, OnInit, Output, ViewChild, inject } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Cart, LineItem} from '../models';
import { CartStore } from '../cart.store';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-order-form',
  templateUrl: './order-form.component.html',
  styleUrl: './order-form.component.css'
})
export class OrderFormComponent implements OnInit {

  // NOTE: you are free to modify this component

  private fb = inject(FormBuilder)
  private cartSvc = inject(CartStore)

  @Input({ required: true })
  productId!: string

  @Input({ required: true })
  name!: string

  @Input({ required: true })
  price!: number

  form!: FormGroup

  ngOnInit(): void {
    this.form = this.createForm()
  }

  addToCart() {
    const lineItem: LineItem = {
      prodId: this.productId,
      quantity: this.form.value['quantity'],
      name: this.name,
      price: this.price * this.form.value['quantity']
    }
    this.form = this.createForm()

    this.cartSvc.addItem(lineItem)

    console.info(">>>added to cart", lineItem)

  }

  private createForm(): FormGroup {
    return this.fb.group({
      quantity: this.fb.control<number>(1, [ Validators.required, Validators.min(1) ])
    })
  }


}
