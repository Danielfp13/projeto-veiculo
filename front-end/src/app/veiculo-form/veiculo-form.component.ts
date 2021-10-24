import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SomatorioDecada } from './somatorioDecada';
import { SomatorioMarca } from './somatorioMarca';
import { SomatorioSemana } from './somatorioSemana';
import { Veiculo } from './veiculo';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';
import { VeiculoService } from '../service/veiculo.service';
import { SomatorioVendido } from './somatorioVendido';

@Component({
  selector: 'app-veiculo-form',
  templateUrl: './veiculo-form.component.html',
  styleUrls: ['./veiculo-form.component.css']
})
export class VeiculoFormComponent implements OnInit {

  formulario: FormGroup
  somatorioDecadas: SomatorioDecada[] = [];
  somatorioMarcas: SomatorioMarca[] = [];
  somatorioSemanas: SomatorioSemana[] = [];
  somatorioVendidos: SomatorioVendido[] = [];
  veiculo: Veiculo;
  veiculoId: any;

  constructor(private service: VeiculoService,
    private fb: FormBuilder,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {
    this.veiculo = new Veiculo('', '', 0, '', false);
  }


  ngOnInit(): void {

    this.montarFormulario()

    let params: Observable<Params> = this.activatedRoute.params;
    params.subscribe(urlParams => {
      this.veiculoId = urlParams['id'];
      if (this.veiculoId) {
        this.service.getVeiculoById(this.veiculoId).subscribe(
          (response) => {
            this.veiculo = response;
            this.montarFormularioAltear();
          },
          (errorResponse) => {
            console.log(errorResponse)
          }
        );
      }
    });
    this.atualizarRelatorio()
  }



  onSubmit() {
    if (this.veiculoId) {
      const formValues = this.formulario.value;
      this.veiculo = new Veiculo(formValues.veiculo, formValues.marca, formValues.ano, formValues.descricao, formValues.vendido);
      this.service.alterarVeiculo(this.veiculo, this.veiculoId).subscribe(resposta => {
        this.snackBar.open('Veiculo atualizada com sucesso.', 'Sucesso', {
          duration: 2000
        })
        this.atualizarRelatorio();
        this.router.navigate(['/lista']);
      }, responseError => {
        this.snackBar.open('Erro ao atualizar Veiculo.', 'Erro', {
          duration: 2000
        })
      })
    } else {
      const formValues = this.formulario.value;
      this.veiculo = new Veiculo(formValues.veiculo, formValues.marca, formValues.ano, formValues.descricao, formValues.vendido);
      this.service.save(this.veiculo).subscribe(resposta => {
        this.snackBar.open('Veiculo adicionada com sucesso.', 'Sucesso', {
          duration: 2000
        })
        this.atualizarRelatorio();
        this.router.navigate(['/lista']);
      }, responseError => {
        this.snackBar.open('Erro ao adicionar Veiculo.', 'Erro', {
          duration: 2000
        })
      })
    }
  }

  montarFormulario() {
    this.formulario = this.fb.group({
      veiculo: ['', Validators.required],
      marca: ['', Validators.required],
      ano: ['', [Validators.required, Validators.max(9999), Validators.min(0)]],
      vendido: ['', Validators.required],
      descricao: ['']
    })
  }

  findSomatorioDecada() {
    this.service.findDecada().subscribe(resposta => {
      this.somatorioDecadas = resposta
    })
  }

  findSomatorioMarca() {
    this.service.findMarca().subscribe(resposta => {
      this.somatorioMarcas = resposta
      console.log(resposta)
    })
  }

  findSomatorioSemana() {
    this.service.findSemana().subscribe(resposta => {
      this.somatorioSemanas = resposta
    })
  }

  findSomatorioVendido() {
    this.service.findVendido().subscribe(resposta => {
      this.somatorioVendidos = resposta
      console.log(resposta)
      console.log('ola')
    })
  }

  voltarParaListagem() {
    this.router.navigate(['/lista']);
  }

  resetarFormulario() {
    this.formulario.reset();
    this.formulario.controls.veiculo.markAsUntouched
    this.formulario.controls.marca.markAsUntouched
    this.formulario.controls.ano.markAsUntouched
    this.formulario.controls.descricao.markAsUntouched
    this.formulario.controls.vendido.markAsUntouched
    this.atualizarRelatorio()
  }

  montarFormularioAltear() {
    this.formulario.controls.veiculo.setValue(this.veiculo.veiculo)
    this.formulario.controls.marca.setValue(this.veiculo.marca)
    this.formulario.controls.ano.setValue(this.veiculo.ano)
    this.formulario.controls.descricao.setValue(this.veiculo.descricao)
    this.formulario.controls.vendido.setValue(this.veiculo.vendido)
  }

  atualizarRelatorio() {
    this.findSomatorioDecada()
    this.findSomatorioMarca()
    this.findSomatorioSemana()
    this.findSomatorioVendido()
  }

}
