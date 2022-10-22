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
    self.f1 = Funcionario('Adisio', 'Fialho', 'nome@gmail.com', 3200, 'DESENVOLVEDOR')
    self.dev1 = Funcionario('Adisio1', 'Fialho', 'nome@gmail.com', 2000, 'DESENVOLVEDOR')
    
    self.f2 = Funcionario('Fabiana', 'Fialho', 'fabiana@gmail.com', 3200.50, 'DBA')
    self.dba2 = Funcionario('Fabiana1', 'Fialho', 'fabiana@gmail.com', 2000.50, 'DBA')

    self.test1 = Funcionario('Fabiana', 'Fialho', 'fabiana@gmail.com', 3200.50, 'TESTADOR')
    self.test2 = Funcionario('Fabiana1', 'Fialho', 'fabiana@gmail.com', 2000.50, 'TESTADOR')

    self.gerente1 = Funcionario('Fabiana', 'Fialho', 'fabiana@gmail.com', 5200.50, 'GERENTE')
    self.gerente2 = Funcionario('Fabiana1', 'Fialho', 'fabiana@gmail.com', 4000.50, 'GERENTE')

    self.rh = Funcionario('Fabiana', 'Fialho', 'fabiana@gmail.com', 1200.50, 'RH')
    self.cto = Funcionario('Fabiana1', 'Fialho', 'fabiana@gmail.com', 4000.50, 'CTO')
  
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
    self.assertEqual(self.f1.cargo, 'DESENVOLVEDOR')

  def test_pessoa_attr_cargo_e_str(self):
        self.assertIsInstance(self.f1.cargo, str)
        self.assertIsInstance(self.f2.cargo, str)
  
  def test_calcula_salario_dev(self):
    self.assertEqual(Funcionario.calculaSalario(self.f1.cargo, self.f1.salariobase), 2560)
    self.assertEqual(Funcionario.calculaSalario(self.dev1.cargo, self.dev1.salariobase), 1800.00)

  def test_calcula_salario_dba(self):
    self.assertEqual(Funcionario.calculaSalario(self.f2.cargo, self.f2.salariobase), 2560.40)
    self.assertEqual(Funcionario.calculaSalario(self.dba2.cargo, self.dba2.salariobase), 1600.40)

  def test_calcula_salario_testador(self):
    self.assertEqual(Funcionario.calculaSalario(self.test1.cargo, self.test1.salariobase), 2560.40)
    self.assertEqual(Funcionario.calculaSalario(self.test2.cargo, self.test2.salariobase), 1600.40)

  def test_calcula_salario_gerente(self):
    self.assertEqual(Funcionario.calculaSalario(self.gerente1.cargo, self.gerente1.salariobase), 4160.40)
    self.assertEqual(Funcionario.calculaSalario(self.gerente2.cargo, self.gerente2.salariobase), 3600.45)

  def test_calcula_salario_nao_listado(self):
    self.assertEqual(Funcionario.calculaSalario(self.rh.cargo, self.rh.salariobase), 'Funcionario não listado')
    self.assertEqual(Funcionario.calculaSalario(self.cto.cargo, self.cto.salariobase), 'Funcionario não listado')



if __name__ == '__main__':
  unittest.main(verbosity=2)
