package testestefanini.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import testestefanini.model.Usuario;

public class UsuarioDao {
	
	public Usuario obterUsuario(String nome, String senha) throws IOException {
		Usuario usuario = null;
		
		String usuarioArquivo = lerArquivoUsuario();
		if (usuarioArquivo != null) {
			String array[] = new String[2];
			array = usuarioArquivo.split(";");
			String nomeArquivo = array[0];
			String senhaArquivo = array[1];
			
			if (nome.equals(nomeArquivo) && senhaArquivo.equals(senhaArquivo)) {
				usuario = new Usuario();
				usuario.setNome(nomeArquivo);
				usuario.setSenha(senhaArquivo);
			}
		}
		
		return usuario;
	}
	
	private String lerArquivoUsuario() throws IOException {
		File fileUsuario = new File("usuario.txt");
		FileReader fileReaderUsuario = new FileReader(fileUsuario);
		BufferedReader bufferedReaderUsuario = new BufferedReader(fileReaderUsuario);
		
		String usuario = bufferedReaderUsuario.readLine();
		while (usuario != null) {
			usuario = bufferedReaderUsuario.readLine();
		}
		
		fileReaderUsuario.close();
		bufferedReaderUsuario.close();
		
		return usuario;
	}
}
