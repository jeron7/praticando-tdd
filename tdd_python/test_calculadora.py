# TDD - Test driven development (Desenvolvimento baseado em testes)

# RED
# Part 1 -> Criar o testes e ver falhar

# GREEN 
# Part 2 -> Criar o código e ver passar

# REFACTOR
# Part 3 -> Refatorar o código


import unittest

from calculadoraSalario import Funcionario
#Testando para ver se o salario é um inteiro ou float,
# caso contrário, der um erro, pois é impossível calcular.
# class TestCalculaSalario(unittest.TestCase):
#   def test_calcula_salario_levantar_assertion_caso_nao_receba_int_or_float(self):
#     with self.assertRaises(AssertionError):
#       salario_int_or_float('')

# Testando os atributo de funcionario
# o funcionario tem
# Nome = 'str'
# email = 'srt'
# salariobase = 'int ou float'
# cargo = 'str'

class Testfuncionario(unittest.TestCase):
  # Adiconado uma função para adiconar antes de todos os Test
  #Para facilitar o códiogo.
  def setUp(self):
    self.f1 = Funcionario('Adisio', 'Fialho', 'nome@gmail.com', 3200, 'Dev')
  
  def test_funcionario_attr_nome_tem_o_valor_correto(self):
    self.assertEqual(self.f1.nome, 'Adisio')
  
  def test_funcionario_attr_sobrenome_tem_o_valor_correto(self):
    self.assertEqual(self.f1.sobrenome, 'Fialho')
  
  def test_funcionario_attr_email_tem_o_valor_correto(self):
    self.assertEqual(self.f1.email, 'nome@gmail.com')
  
  def test_funcionario_attr_salariobase_tem_o_valor_correto(self):
    self.assertEqual(self.f1.salariobase, 3200)

  def test_funcionario_attr_cargo_tem_o_valor_correto(self):
    self.assertEqual(self.f1.cargo, 'Dev')


if __name__ == '__main__':
  unittest.main(verbosity=2)
