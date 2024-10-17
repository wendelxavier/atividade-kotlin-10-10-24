import java.util.*

data class Produto(
    val codigo: String,
    var nome: String,
    var valorUnitario: Double
)

class Lojinha {
    private val produtos = mutableListOf<Produto>()
    private val vendas = mutableListOf<Pair<Produto, Int>>() // Usando Pair para simplificar

    // Cadastrar um novo produto
    fun cadastrarProduto(codigo: String, nome: String, valorUnitario: Double) {
        val produto = Produto(codigo, nome, valorUnitario)
        produtos.add(produto)
        println("Produto cadastrado: $produto")
    }

    // Alterar um produto
    fun alterarProduto(codigo: String, novoNome: String, novoValor: Double) {
        val produto = produtos.find { it.codigo == codigo }
        if (produto != null) {
            produto.nome = novoNome
            produto.valorUnitario = novoValor
            println("Produto alterado: $produto")
        } else {
            println("Produto não encontrado.")
        }
    }

    // Excluir um produto
    fun excluirProduto(codigo: String) {
        val produto = produtos.find { it.codigo == codigo }
        if (produto != null) {
            produtos.remove(produto)
            println("Produto excluído: $produto")
        } else {
            println("Produto não encontrado.")
        }
    }

    // Realizar uma venda
    fun realizarVenda(codigo: String, quantidade: Int) {
        val produto = produtos.find { it.codigo == codigo }
        if (produto != null) {
            vendas.add(Pair(produto, quantidade))
            println("Item adicionado à venda: ${produto.nome} - Quantidade: $quantidade")
        } else {
            println("Produto não encontrado.")
        }
    }

    // Finalizar a compra
    fun finalizarCompra() {
        var total = 0.0
        println("Itens da compra:")
        for ((produto, quantidade) in vendas) {
            val subtotal = produto.valorUnitario * quantidade
            total += subtotal
            println("${quantidade} x ${produto.nome} - R$ ${subtotal}")
        }
        println("Total: R$ $total")
        definirFormaPagamento(total)
        vendas.clear() // Limpa a lista de vendas após finalizar a compra
    }

    // Definir a forma de pagamento
    private fun definirFormaPagamento(total: Double) {
        println("Escolha a forma de pagamento:")
        println("1. Pix")
        println("2. Cartão")
        println("3. Dinheiro")
        val scanner = Scanner(System.`in`)
        when (scanner.nextInt()) {
            1 -> println("Código Pix: 123456789")
            2 -> {
                println("Digite os dados do cartão (número, validade, CVV):")
                val dadosCartao = scanner.next() // Simulação de entrada de dados do cartão
                println("Pagamento realizado com sucesso com o cartão: $dadosCartao")
            }
            3 -> {
                println("Valor pago:")
                val valorPago = scanner.nextDouble()
                if (valorPago >= total) {
                    val troco = valorPago - total
                    println("Troco: R$ $troco")
                } else {
                    println("Valor pago é insuficiente.")
                }
            }
            else -> println("Opção inválida.")
        }
    }
}

fun main() {
    val lojinha = Lojinha()
    val scanner = Scanner(System.`in`)

    // Cadastro de produtos
    while (true) {
        println("Deseja cadastrar um produto? (s/n)")
        if (scanner.next() == "n") break

        println("Digite o código do produto:")
        val codigo = scanner.next()
        println("Digite o nome do produto:")
        val nome = scanner.next()
        println("Digite o valor unitário do produto:")
        val valorUnitario = scanner.nextDouble()

        lojinha.cadastrarProduto(codigo, nome, valorUnitario)
    }

    // Realizar vendas
    while (true) {
        println("Deseja realizar uma venda? (s/n)")
        if (scanner.next() == "n") break

        println("Digite o código do produto:")
        val codigoVenda = scanner.next()
        println("Digite a quantidade:")
        val quantidade = scanner.nextInt()

        lojinha.realizarVenda(codigoVenda, quantidade)
    }

    // Finalizar a compra
    lojinha.finalizarCompra()
}
