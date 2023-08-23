package br.com.cotiinformatica.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import br.com.cotiinformatica.entities.Usuario;

public class UsuarioRepository {
	// atributo
	private JdbcTemplate jdbcTemplate;

	// m�todo construtor para recebermos a conex�o com o banco de dados
	// ou seja, recebermos o DATA SOURCE configurado na classe
	// MvcConfiguration.java
	public UsuarioRepository(DataSource dataSource) {
		// inicialiando o atributo jdbcTemplate passando para ele
		// o DATA SOURCE (conex�o com o banco de dados)
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// m�todo para inserir um usu�rio no banco de dados
	public void create(Usuario usuario) throws Exception {
		// escrevendo a instru��o SQL que ser� executada no banco de dados
		String sql = "insert into usuario(nome, email, senha) values(?,?,?)";
		// definindo quais dados/par�metros ser�o gravados na tabela
		Object[] params = { usuario.getNome(), usuario.getEmail(), usuario.getSenha() };
		// executando no banco de dados
		jdbcTemplate.update(sql, params);
	}

	// m�todo para consultar 1 usu�rio atrav�s do email
	public Usuario find(String email) throws Exception {
		// escrevendo a instru��o SQL que ser� executada no banco de dados
		String sql = "select * from usuario where email = ?";
		// definindo quais dados/par�metros ser�o passados na query
		Object[] params = { email };
		// executando a consulta e obtendo os dados da tabela
		List<Usuario> resultado = jdbcTemplate.query(sql, params, new RowMapper<Usuario>() {
			// m�todo para ler cada registro obtido da consulta no banco de dados
			@Override
			public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				return usuario;
			}
		});
		// verificando se algum usu�rio foi encontrado
		if (resultado.size() > 0)
			return resultado.get(0); // retornar o primeiro usu�rio contido na lista
		else
			return null; // retornar vazio
	}

	// m�todo para consultar 1 usu�rio atrav�s do email e da senha
	public Usuario find(String email, String senha) throws Exception {
		// escrevendo a instru��o SQL que ser� executada no banco de dados
		String sql = "select * from usuario where email = ? and senha = ?";
		// definindo quais dados/par�metros ser�o passados na query
		Object[] params = { email, senha };
		// executando a consulta e obtendo os dados da tabela
		List<Usuario> resultado = jdbcTemplate.query(sql, params, new RowMapper<Usuario>() {
			// m�todo para ler cada registro obtido da consulta no banco de dados
			@Override
			public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				return usuario;
			}
		});
		// verificando se algum usu�rio foi encontrado
		if (resultado.size() > 0)
			return resultado.get(0); // retornar o primeiro usu�rio contido na lista
		else
			return null; // retornar vazio
	}
}
