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
    self.f2 = Funcionario('Fabiana', 'Fialho', 'fabiana@gmail.com', 3200.50, 'DBA')
  
  def test_funcionario_attr_nome_tem_o_valor_correto(self):
    self.assertEqual(self.f1.nome, 'Adisio')
  
  def test_pessoa_attr_nome_e_str(self):
        self.assertIsInstance(self.f1.nome, str)
        self.assertIsInstance(self.f2.nome, str)

  def test_funcionario_attr_sobrenome_tem_o_valor_correto(self):
    self.assertEqual(self.f1.sobrenome, 'Fialho')
  
  def test_pessoa_attr_nome_e_str(self):
        self.assertIsInstance(self.f1.sobrenome, str)
        self.assertIsInstance(self.f2.sobrenome, str)

  def test_funcionario_attr_email_tem_o_valor_correto(self):
    self.assertEqual(self.f1.email, 'nome@gmail.com')
  
  def test_pessoa_attr_email_e_str(self):
        self.assertIsInstance(self.f1.email, str)
        self.assertIsInstance(self.f2.email, str)

  def test_funcionario_attr_salariobase_tem_o_valor_correto(self):
    self.assertEqual(self.f1.salariobase, 3200)

  def test_pessoa_attr_salariobase_e_int_or_float(self):
        self.assertIsInstance(self.f1.salariobase, (int, float))
        self.assertIsInstance(self.f2.salariobase, (int, float))

  def test_funcionario_attr_cargo_tem_o_valor_correto(self):
    self.assertEqual(self.f1.cargo, 'Dev')

  def test_pessoa_attr_cargo_e_str(self):
        self.assertIsInstance(self.f1.cargo, str)
        self.assertIsInstance(self.f2.cargo, str)


if __name__ == '__main__':
  unittest.main(verbosity=2)
