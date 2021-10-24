import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Veiculo } from '../veiculo-form/veiculo'
import { environment } from '../../environments/environment'
import { PaginaVeiculo } from '../veiculo-form/paginaVeiculo';

@Injectable({
  providedIn: 'root'
})
export class VeiculoService {

  private uri: string = environment.apiBaseUrl;
  veiculo: Veiculo

  constructor(private http: HttpClient) { }

  save(veiculo: Veiculo): Observable<Veiculo>{
    return this.http.post<Veiculo>(this.uri, veiculo)
  }

  getVeiculoById(id: any): Observable<Veiculo>{
    return this.http.get<Veiculo>(`${this.uri}/${id}`);
  }

  alterarVeiculo(veiculo: Veiculo, id: any):Observable<any>{
    return this.http.put<any>(`${this.uri}/${id}`,veiculo);
  }


  findDecada(): Observable<any>{
    return this.http.get<any>(`${this.uri}/decadas`);
  }

  findMarca(): Observable<any>{
    return this.http.get<any>(`${this.uri}/marcas`);
  }

  findSemana(): Observable<any>{
    return this.http.get<any>(`${this.uri}/semanas`);
  }

  findVendido(): Observable<any>{
    return this.http.get<any>(`${this.uri}/vendidos`);
  }

  findPage(page: number, linePerPage: number, direction: string, orderBy: string, id: string, marca: string, ultimaSemana: string ): Observable<PaginaVeiculo>{
    const params = new HttpParams()
      .set('page', page.toString())
      .set('linePerPage' , linePerPage.toString())
      .set( 'direction', direction)
      .set( 'orderBy', orderBy)
      .set( 'id', id)
      .set( 'marca', marca)
      .set( 'ultimaSemana', ultimaSemana)

    return this.http.get<any>(`${this.uri}/find?${params.toString()}`);
  }

  deleteById(id: any) : Observable<any>{
    return this.http.delete<any>(`${this.uri}/${id}`);
  }

  recebeVeiculo(veiculo: Veiculo){
    this.veiculo = veiculo
  }
}
