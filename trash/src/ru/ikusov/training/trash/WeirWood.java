package ru.ikusov.training.trash;

public class WeirWood<K extends Comparable<K>, V> {

    private Node<K, V> root = null;

    private WeirWood(Node<K, V> root) {
        this.root = root;
    }

    public WeirWood() {
        this(null);
    }

    private static int level = 0;

    private static String[] levelString;

    private class Node<K, V> {
        K key;
        V value;
        Node<K, V> left, right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V> find(K key) {
        Node<K, V> node = this.root;
        while (node != null) {
            int compare = key.compareTo(node.key);
            if (compare < 0) {
                node = node.left;
            }
            if (compare > 0) {
                node = node.right;
            }
            if (compare == 0) {
                break;
            }
        }
        return node;
    }

    public V getValue(K key) {
        Node<K, V> found = find(key);
        return found == null ? null : found.value;
    }

    public void insert(K key, V value) {
        Node<K, V> node = this.root, prev = node;
        int compare;

        if (node == null) {
            this.root = new Node<>(key, value);
            return;
        }

        while (node != null) {
            compare = key.compareTo(node.key);
            if (compare == 0) {
                node.value = value;
                return;
            }
            prev = node;
            if (compare > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        compare = key.compareTo(prev.key);
        if (compare < 0) {
            prev.left = new Node<>(key, value);
        } else {
            prev.right = new Node<>(key, value);
        }
    }

    private int size(Node<K, V> root) {
        int size = 0;
        Node<K, V> node = root;
        if (node != null)
            size = 1 + size(node.left) + size(node.right);
        return size;
    }

    public int size() {
        return size(this.root);
    }

    private int width(Node<K, V> root) {
        return root == null ? 4 :
                root.left == null && root.right == null ? root.value.toString().length() :
                        width(root.left) + width(root.right);
    }

    public int width() {
        return width(this.root);
    }

    private int height(Node<K, V> root)
    {
        int height=1;
        height += root == null ? 0 :
                Math.max(height(root.right), height(root.left));
        return height;
    }

    public int height() {
        return height(this.root);
    }

    private String getString() {
        int height = height();
        StringBuilder string = new StringBuilder();
        levelString = new String[height];

        Node<K, V> node = this.root;
        int i = 0;
        while(node!=null) {
            if(node.left == null) {
                levelString[i] = "";
            } else {
                levelString[i] = " ".repeat(width(node.left));
            }
            levelString[i] += node.value + "\n";
            i++;
            node = node.left;
        }

        for (String s : levelString) {
            string.append(s);
        }

        return string.toString();
    }

    private void getString(Node <K, V> root, int i) {
        if (root == null) {
            levelString[i] += "null";
        }
    }

    public String toString() {
        return getString();
    }
}
