import { api } from './api'

export default {

  salvar: (obj) => {
    return api.post('veiculo', obj)
  },

  atualizar: (obj) => {
    return api.put('veiculo/' + obj.codigo, obj)
  },

  apagar: (obj) => {
    return api.delete('veiculo/' + obj.codigo)
  },

  listarVeiculo: (obj) => {
    return api.get('veiculo/' + obj.codigo)
  },

  listarTodos: () => {
    return api.get('veiculo')
  },

  listarVendidos: (lines, page, direction) => {
    return api.get('/veiculo/vendido/?linesPerPage=' + lines
      + '&page=' + page + '&direction=' + direction)
  },

  listarVenda: (lines, page, direction) => {
    return api.get('/veiculo/venda/?linesPerPage=' + lines
      + '&page=' + page + '&direction=' + direction)
  },

  buscaPersonalizada: (lines, page, direction) => {
    return api.get('/veiculo/page?linesPerPage=' + lines
      + '&page=' + page + '&direction=' + direction)
  }
}