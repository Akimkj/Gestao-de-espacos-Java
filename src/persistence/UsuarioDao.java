package persistence;
import java.io.*;
import java.util.ArrayList;
import  java.util.List;
import models.Usuario;

public class UsuarioDao {
    private final String ARQUIVO = "..\\data\\usuarios.txt";


    //Lista os usuarios do arquivo;
    public List<Usuario> listarTodos (){
        List<Usuario> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                int id = Integer.parseInt(partes[0]);
                String nome = partes[1];
                String email = partes[2];
                String senha = partes[3];
                boolean edADM = Boolean.parseBoolean(partes[4]);

                lista.add(new Usuario(id, nome, email, senha, edADM));
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo " + e.getMessage());

        }
        return lista;
    }

    public void salvar(Usuario usuario) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            String linha = usuario.getId() + ";" + usuario.getNome() + ";" +usuario.getEmail() + ";" + usuario.getSenha() + ";" + usuario.getEhADM();
            bw.write(linha);
            bw.newLine();
        } catch (IOException e){
            System.out.println("Erro ao salvar usuario: " + e.getMessage());
        }
    }

    public void remover(int idPAraRemover) {
        List<Usuario> usuarios = listarTodos();
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Usuario u : usuarios){
                if (u.getId() == idPAraRemover) {
                    String linha = u.getId() + ";" +u.getNome() + ";" + u.getEmail() + ";" + u.getSenha() + u.getEhADM();
                    bw.write(linha);
                    bw.newLine();
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao remover usuario: " + e.getMessage());
        }
    }

    public Usuario buscarPorId(int idBuscado) {
        for (Usuario u : listarTodos()){
            if(u.getId() == idBuscado){
                return u;
            }
        }
        return null;
    }

}
