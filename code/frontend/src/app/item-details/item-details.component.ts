import { Component, OnInit } from '@angular/core';
import { Item } from '../item';
import { ActivatedRoute } from '@angular/router';
import { ItemService } from '../item.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-item-details',
  templateUrl: './item-details.component.html',
  styleUrls: ['./item-details.component.css']
})
export class ItemDetailsComponent implements OnInit {

  id!: number;
  item!: Item;
  constructor(private route: ActivatedRoute, private itemService: ItemService,
    private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.item = new Item();
    this.itemService.getItemById(this.id).subscribe( data => {
      this.item = data;
    })
  }

  updateItem(id: number){
    this.router.navigate(['update-item', id]);
  }

}
