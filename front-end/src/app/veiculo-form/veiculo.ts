export class Veiculo{
  id: number;
  veiculo: string;
  marca: string;
  ano: number;
  descricao: string;
  vendido: boolean;
  created: any;
  updated: any;

  constructor(veiculo: string, marca: string, ano: number, descricao: string, vendido: boolean){
     this.veiculo = veiculo;
     this.marca = marca;
     this.ano = ano;
     this.descricao = descricao;
     this.vendido = vendido;
 }
}
