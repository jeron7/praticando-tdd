class Funcionario:
  def __init__(self, nome, sobrenome, email, salariobase, cargo):
    self.nome = nome
    self.sobrenome = sobrenome
    self.email = email
    self.salariobase = salariobase
    self.cargo = cargo



  def calculaSalario(cargo, salariobase):
    if cargo == 'DESENVOLVEDOR':
      if salariobase >= 3000:
        desconto = salariobase * 0.20
        salarioLiquido = salariobase - desconto
        return salarioLiquido
      else:
        desconto = salariobase * 0.10
        salarioLiquido = salariobase - desconto
        return salarioLiquido   
    elif cargo == 'DBA' or cargo == 'TESTADOR':
      if salariobase >= 2000:
        desconto = salariobase * 0.20
        salarioLiquido = salariobase - desconto
        return salarioLiquido
      else:
        desconto = salariobase * 0.10
        salarioLiquido = salariobase - desconto
        return salarioLiquido  
    elif cargo == 'GERENTE':
      if salariobase >= 5000:
        desconto = salariobase * 0.20
        salarioLiquido = salariobase - desconto
        return salarioLiquido
      else:
        desconto = salariobase * 0.10
        salarioLiquido = salariobase - desconto
        return salarioLiquido 
    else:
      return 'Funcionario não listado'     