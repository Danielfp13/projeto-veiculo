import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { VeiculoService } from '../service/veiculo.service';
import { Veiculo } from '../veiculo-form/veiculo';
import { DialogExcluirComponent } from '../dialog-excluir/dialog-excluir.component';

@Component({
  selector: 'app-veiculo-lista',
  templateUrl: './veiculo-lista.component.html',
  styleUrls: ['./veiculo-lista.component.css']
})
export class VeiculoListaComponent implements OnInit {


  opcoes: boolean[] = [true, false];

  menssagemDeResposta: string
  veiculos: Veiculo[] = [];
  busca: string = ""
  semana: string = "false"

  page: number = 0;
  linePerPage: number = 8;
  direction: string = 'ASC';
  orderBy: string = 'id';
  totalElementos: number = 0;
  totalPage: number = 0;
  pageSizeOptions: number[] = [8]

  colunas = ['id', 'veiculo', 'marca', 'ano', 'descricao', 'vendido', 'alterar'];

  constructor(
    private service: VeiculoService,
    private router: Router,
    public dialog: MatDialog,
    private snackBar: MatSnackBar
  ) {

  }

  ngOnInit(): void {
    this.findPage(this.page, this.linePerPage, this.direction, this.orderBy, this.busca, this.busca, this.semana);
  }

  findPage(page: number, linePerPage: number, direction: string, orderBy: string, id: string, marca: string, ultimaSemana: string) {
    this.service.findPage(page, linePerPage, direction, orderBy, id, marca, ultimaSemana).subscribe(
      response => {
        this.veiculos = response.content
        this.totalElementos = response.totalElements
        this.totalPage = response.totalPages
        this.page = response.number
      }, responseError => {

      }
    );
  }

  paginar(event: PageEvent) {
    this.page = event.pageIndex
    this.findPage(this.page, this.linePerPage, this.direction, this.orderBy, this.busca, this.busca, this.semana);
  }

  novoCadastro() {
    this.router.navigate(['/form'])
  }

  openDialog(veiculo: Veiculo) {
    let dialogRef = this.dialog.open(DialogExcluirComponent, { data: { Veiculo: veiculo } });
    dialogRef.afterClosed().subscribe(response => {
      if (response === "true") {
        this.deletar(veiculo)
      }
    })
  }

  deletar(veiculo: Veiculo) {
    this.service.deleteById(veiculo.id).subscribe(
      (response) => {
        this.snackBar.open('Veiculo excluida com sucesso.', 'Sucesso', {
          duration: 3000
        })
        this.findPage(this.page, this.linePerPage, this.direction, this.orderBy, this.busca, this.busca, this.semana);
      }, (errorResponse) => {
        this.snackBar.open('Erro ao excluir veiculo.', 'Erro', {
          duration: 3000
        })
      }
    )
  }
  buscar() {
    this.findPage(0, this.linePerPage, this.direction, this.orderBy, this.busca, this.busca, this.semana);
  }
}

