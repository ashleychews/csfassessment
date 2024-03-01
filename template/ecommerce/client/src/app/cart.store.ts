
// TODO Task 2

import { ComponentStore } from "@ngrx/component-store";
import { Cart, LineItem } from "./models";
import { Injectable } from "@angular/core";

const INIT_STORE: Cart = {
    lineItems: []
}

@Injectable()
// Use the following class to implement your store
export class CartStore extends ComponentStore<Cart>{

    //constructor
    constructor() { super(INIT_STORE) }

    //mutators
    //add items into cart
    readonly addItem = this.updater<LineItem>(
        (slice: Cart, lineitem: LineItem) => {
            return {
                lineItems: [...slice.lineItems, lineitem]
            }
        }
    )

    //selectors
    //update items in cart
    //quantity is no of products, not quantity
    //if breadx2, applex2 => items in cart is 2
    readonly getItemsinCart = this.select<number> (
        (slice: Cart) => slice.lineItems.length
    )


    //selectors
    //get lineitems for checkout
    readonly getLineItems = this.select<LineItem[]>(
        (slice: Cart) => slice.lineItems
    )


}
