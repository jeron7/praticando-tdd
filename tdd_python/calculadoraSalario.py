class Funcionario:
  def __init__(self, nome, sobrenome, email, salarioBase, cargo):
    self.nome = nome
    self.sobrenome = sobrenome
    self.email = email
    self.salarioBase = salarioBase
    self.cargo = cargo


  def calculaDesconto(cargo, salarioBase):
    desconto = 0
    if cargo == 'DESENVOLVEDOR':
      if salarioBase >= 3000:
        desconto = salarioBase * 0.20
      else:
        desconto = salarioBase * 0.10
    elif cargo == 'DBA' or cargo == 'TESTADOR':
      if salarioBase >= 2000:
        desconto = salarioBase * 0.20
      else:
        desconto = salarioBase * 0.10
    elif cargo == 'GERENTE':
      if salarioBase >= 5000:
        desconto = salarioBase * 0.20
      else:
        desconto = salarioBase * 0.10
    return desconto

  def calculaSalario(cargo, salarioBase):
    if cargo == 'DESENVOLVEDOR':
      if salarioBase >= 3000:
        desconto = salarioBase * 0.20
        salarioLiquido = salarioBase - desconto
        return salarioLiquido
      else:
        desconto = salarioBase * 0.10
        salarioLiquido = salarioBase - desconto
        return salarioLiquido   
    elif cargo == 'DBA' or cargo == 'TESTADOR':
      if salarioBase >= 2000:
        desconto = salarioBase * 0.20
        salarioLiquido = salarioBase - desconto
        return salarioLiquido
      else:
        desconto = salarioBase * 0.10
        salarioLiquido = salarioBase - desconto
        return salarioLiquido  
    elif cargo == 'GERENTE':
      if salarioBase >= 5000:
        desconto = salarioBase * 0.20
        salarioLiquido = salarioBase - desconto
        return salarioLiquido
      else:
        desconto = salarioBase * 0.10
        salarioLiquido = salarioBase - desconto
        return salarioLiquido 
    else:
      return 'Funcionario não listado'     