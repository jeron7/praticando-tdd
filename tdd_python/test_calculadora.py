# TDD - Test driven development (Desenvolvimento baseado em testes)

# RED
# Part 1 -> Criar o testes e ver falhar

# GREEN 
# Part 2 -> Criar o código e ver passar

# REFACTOR
# Part 3 -> Refatorar o código


import unittest
from calculadoraSalario import salario_int_or_float
#Testando para ver se o salario é um inteiro ou float,
# caso contrário, der um erro, pois é impossível calcular.
class TestCalculaSalario(unittest.TestCase):
  def test_calcula_salario_levantar_assertion_caso_nao_receba_int_or_float(self):
    with self.assertRaises(AssertionError):
      salario_int_or_float('')

unittest.main(verbosity=2)