package AVLTree;

import BinaryTree.BinaryNode;
import RedBlackTree.RedBlackNode;

public class AVLTree {
    private AVLNode root;

    public AVLNode inserir(int val) {
        System.out.println("INSERINDO O NÓ: " + val);
        this.root = this.inserir(this.root, val);
        System.out.println("ARVORE: (POS ORDER)");
        System.out.println("-------------------------------------------------");
        this.transversePostOrder();
        System.out.println("-------------------------------------------------");
        System.out.println();
        return this.root;
    }

    public AVLNode remover(int val) {
        this.root = this.remover(this.root, val);
        return this.root;
    }

    private AVLNode inserir(AVLNode raiz, int chave) {
        // Inserção normal em uma árvore binária de busca
        if (raiz == null) {
            return new AVLNode(chave);
        }

        if (chave < raiz.getVal()) {
            raiz.setLeft(inserir(raiz.getLeft(), chave));
        } else if (chave > raiz.getVal()) {
            raiz.setRight(inserir(raiz.getRight(), chave));
        } else {
            // Se o valor já existe, retornar o nó atual
            System.out.println("NÓ JÁ EXISTE. NÃO HÁ O QUE FAZER.");
            return raiz;
        }
        raiz = balancear(raiz);
        return raiz;
    }


    private AVLNode remover(AVLNode raiz, int chave) {
        // 1. Remoção padrão em uma árvore binária de busca
        if (raiz == null) {
            return raiz; // Valor não encontrado
        }

        // Navegar para o nó a ser removido
        if (chave < raiz.getVal()) {
            raiz.setLeft(remover(raiz.getLeft(), chave));
        } else if (chave > raiz.getVal()) {
            raiz.setRight(remover(raiz.getRight(), chave));
        } else {
            // Nó encontrado
            // Caso 1: Nó folha ou com um filho
            if (raiz.getLeft() == null || raiz.getRight() == null) {
                AVLNode temp = (raiz.getLeft() != null) ? raiz.getLeft() : raiz.getRight();
                if (temp == null) { // Nó folha
                    temp = raiz;
                    raiz = null;
                } else { // Nó com um filho
                    raiz = temp;
                }
            } else {
                // Caso 2: Nó com dois filhos
                AVLNode temp = encontrarMinimo(raiz.getRight()); // Encontrar o sucessor inorder
                raiz.setVal(temp.getVal());
                raiz.setRight(remover(raiz.getRight(), temp.getVal()));
            }
        }

        // Se a árvore tinha apenas um nó, retornar null
        if (raiz == null) {
            return raiz;
        }

        raiz = this.balancear(raiz);
        return raiz;
    }

    private AVLNode rotacaoDuplaDireita(AVLNode node) {
        node.setLeft(rotacaoSimplesEsquerda(node));
        return rotacaoSimplesDireita(node);
    }

    private AVLNode rotacaoDuplaEsquerda(AVLNode node) {
        node.setRight(rotacaoSimplesDireita(node));
        return rotacaoSimplesEsquerda(node);
    }

    private AVLNode rotacaoSimplesDireita(AVLNode node) {
        AVLNode novaRaiz = node.getLeft();
        node.setLeft(novaRaiz.getRight());
        novaRaiz.setRight(node);
        atualizarAltura(node);
        atualizarAltura(novaRaiz);
        return novaRaiz;
    }

    private AVLNode rotacaoSimplesEsquerda(AVLNode node) {
        AVLNode novaRaiz = node.getRight();
        node.setRight(novaRaiz.getLeft());
        novaRaiz.setLeft(node);
        atualizarAltura(node);
        atualizarAltura(novaRaiz);
        return novaRaiz;
    }

    private int calcularFatorBalanceamento(AVLNode node) {
        if (node == null) return 0;
        int l = altura(node.getLeft());
        int r = altura(node.getRight());
        return r - l;
    }

    private void atualizarAltura(AVLNode node) {
        if (node != null) {
            int l = altura(node.getLeft());
            int r = altura(node.getRight());
            node.setHeight(1 + (l > r ? l : r));
        }
    }

    public int calcularAltura(AVLNode node) {
        if (node == null) return 0;
        int l = calcularAltura(node.getLeft());
        int r = calcularAltura(node.getRight());
        return 1 + (l > r ? l : r);
    }

    private AVLNode encontrarMinimo(AVLNode node) {
        AVLNode current = node;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

    private AVLNode balancear(AVLNode node) {
        atualizarAltura(node);
        int balanceamento = calcularFatorBalanceamento(node);

        if (balanceamento > 1 && calcularFatorBalanceamento(node.getRight()) >= 0) {
            System.out.println("Rotação Simples à Esquerda - Nó crítico: " + node.getVal());
            return rotacaoSimplesEsquerda(node);
        }

        if (balanceamento > 1 && calcularFatorBalanceamento(node.getRight()) < 0) {
            System.out.println("Rotação Dupla à Esquerda - Nó crítico: " + node.getVal());
            node.setRight(rotacaoSimplesDireita(node.getRight()));
            return rotacaoSimplesEsquerda(node);
        }

        if (balanceamento < -1 && calcularFatorBalanceamento(node.getLeft()) <= 0) {
            System.out.println("Rotação Simples à Direita - Nó crítico: " + node.getVal());
            return rotacaoSimplesDireita(node);
        }

        if (balanceamento < -1 && calcularFatorBalanceamento(node.getLeft()) > 0) {
            System.out.println("Rotação Dupla à Direita - Nó crítico: " + node.getVal());
            node.setLeft(rotacaoSimplesEsquerda(node.getLeft()));
            return rotacaoSimplesDireita(node);
        }

        return node;
    }


    public int altura(AVLNode node) {
        if (node == null) return 0;
        return node.getHeight();
    }

    public void transversePreOrder() {
        transversePreOrder(this.root);
    }

    private void transversePreOrder(AVLNode node) {
        if (node != null) {
            printNode(node);
            transversePreOrder(node.getLeft());
            transversePreOrder(node.getRight());
        }
    }

    public void transversePostOrder() {
        transversePostOrder(this.root);
    }

    private void transversePostOrder(AVLNode node) {
        if (node != null) {
            transversePostOrder(node.getLeft());
            transversePostOrder(node.getRight());
            printNode(node);
        }
    }

    public void transverseInOrder() {
        this.transverseInOrder(this.root);
    }

    private void transverseInOrder(AVLNode node) {
        if (node != null) {
            transversePostOrder(node.getLeft());
            printNode(node);
            transversePostOrder(node.getRight());
        }
    }

    private void printNode(AVLNode node) {
        if (node != null) {
            System.out.println("NÓ: " + node.getVal());
            System.out.println("FATOR DE BALANCEAMENTO: " + calcularFatorBalanceamento(node));
            System.out.println();
        }
    }

    public void avaregeValuesByLevel() {
        var nodes = this.getAllNodes();
        int size = this.getSize();
        int[] sum = new int[size];
        int[] count = new int[size];
        for (AVLNode node : nodes) {
            int level = getLevel(node);
            sum[level] += node.getVal();
            count[level]++;
        }

        for (int i = 0; i < sum.length; i++) {
            if (count[i] > 0) {
                System.out.println("Media dos valores no nível: " + i + ": " + (sum[i] * 1.0 / (double) count[i]));
                System.out.println();
            }
        }
    }

    public AVLNode[] getAllNodes() {
        int size = this.getSize();
        AVLNode[] nodes = new AVLNode[size];
        getAllNodes(this.root, nodes, 0);
        return nodes;
    }

    private int getAllNodes(AVLNode node, AVLNode[] nodes, int index) {
        if (node == null) return index;
        index = getAllNodes(node.getLeft(), nodes, index);
        nodes[index++] = node;
        index = getAllNodes(node.getRight(), nodes, index);
        return index;
    }

    public int getSize() {
        return this.getSize(this.root);
    }

    private int getSize(AVLNode node) {
        if (node == null) return 0;
        int l = this.getSize(node.getLeft());
        int r = this.getSize(node.getRight());
        return l + r + 1;
    }

    public int getLevel(AVLNode node) {
        return getLevel(node, this.root, 1);
    }

    private int getLevel(AVLNode node, AVLNode current, int level) {
        if (current == null) return -1; // Não encontrado
        if (current == node) return level;
        int leftLevel = getLevel(node, current.getLeft(), level + 1);
        if (leftLevel != -1) return leftLevel;
        return getLevel(node, current.getRight(), level + 1);
    }

    public void printNodesWithLevels() {
        this.printNodesWithLevels(this.root, 1);
    }

    private void printNodesWithLevels(AVLNode node, int level) {
        if (node == null) return;
        System.out.println("NODE: " + node.getVal() + "\nNIVEL: " + level);
        System.out.println();
        printNodesWithLevels(node.getRight(), level + 1);
        printNodesWithLevels(node.getLeft(), level + 1);
    }

    public AVLNode getRoot() {
        return this.root;
    }
}
