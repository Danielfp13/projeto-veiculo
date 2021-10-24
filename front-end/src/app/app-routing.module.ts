import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { VeiculoListaComponent } from './veiculo-lista/veiculo-lista.component';
import { VeiculoFormComponent } from './veiculo-form/veiculo-form.component';

const routes: Routes = [
  { path: 'form', component: VeiculoFormComponent },
  { path: 'form/:id', component: VeiculoFormComponent },
  { path: 'lista', component: VeiculoListaComponent },
  { path: '', redirectTo: '/lista', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
