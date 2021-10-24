import { Component, OnInit, Inject} from '@angular/core';
import { Veiculo } from '../veiculo-form/veiculo';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-excluir',
  templateUrl: './dialog-excluir.component.html',
  styleUrls: ['./dialog-excluir.component.css']
})

export class DialogExcluirComponent implements OnInit {

  veiculo: Veiculo
  constructor( @Inject (MAT_DIALOG_DATA) public data:any) { }

  ngOnInit(): void {
    this.veiculo = this.data.Veiculo
  }

}
