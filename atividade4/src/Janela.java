import javax.swing.*;
import javax.swing.plaf.basic.BasicMenuBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Janela extends JFrame {
    private boolean darkMode = false;
    private JMenuBar menuBar;
    private JEditorPane textArea;
    private boolean fontMode;

    public Janela() {
        super("Atividade 4");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(getTextArea());
        this.setJMenuBar(getTopBar());
        this.setSize(640, 480);
        this.setPreferredSize(new Dimension(640, 480));
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.pack();
    }

    private JMenuBar getTopBar() {
        JMenu menu = new JMenu("Acessibilidade");
        JMenuItem altoContraste = new JMenuItem("Modo alto contraste");
        JMenuItem modoAmpliado = new JMenuItem("Modo ampliado");
        menuBar = new JMenuBar();
        menuBar.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));

        altoContraste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                darkMode = !darkMode;
                setDarkMode();
            }
        });
        modoAmpliado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fontMode = !fontMode;
                setFontMode();
            }
        });

        menu.add(altoContraste);
        menu.add(modoAmpliado);
        menuBar.add(menu);
        setDarkMode();
        return menuBar;
    }

    private void setFontMode() {
        Font font;
        if (fontMode)
            font = new java.awt.Font("Arial", Font.BOLD, 24);
        else
            font = new java.awt.Font("Arial", Font.BOLD, 12);

        textArea.setFont(font);
        MenuElement[] menus = menuBar.getSubElements();
        for (MenuElement menuElement : menus) {
            JMenu menu = (JMenu) menuElement.getComponent();
            menu.setFont(font);
            MenuElement[] menuElements = menu.getSubElements();
            for (MenuElement popupMenuElement : menuElements) {
                JPopupMenu popupMenu = (JPopupMenu) popupMenuElement.getComponent();
                popupMenu.setFont(font);

                MenuElement[] menuItens = popupMenuElement.getSubElements();
                for (MenuElement menuItemElement : menuItens) {
                    JMenuItem menuItem = (JMenuItem) menuItemElement.getComponent();
                    menuItem.setFont(font);
                }
            }
        }
    }

    private JEditorPane getTextArea() {
        textArea = new JEditorPane();
        textArea.setText("Depois da promulgação da Constituição, a Lei nº 10.098 foi o primeiro avanço efetivo na" +
                "legislação brasileira em relação à acessibilidade. Ela estabelece normas gerais e critérios" +
                "básicos para a promoção da acessibilidade das pessoas com deficiência ou com mobilidade" +
                "reduzida e dá outras providências.\n\n" +
                "Essa lei foi regulamentada pelo Decreto nº 5.296, de 2 de dezembro de 2004. Este decreto" +
                "representou um grande avanço, pois estabelece, no seu conceito de acessibilidade, a" +
                "“utilização, com segurança e autonomia, […] dos dispositivos, sistemas e meios de" +
                "comunicação e informação”.\n\n" +
                "    https://mwpt.com.br/acessibilidade-digital/leis-federais-sobre-acessibilidade-na-web/\n\n");
        textArea.setEditable(false);
        textArea.setFont(new java.awt.Font("Arial", Font.PLAIN, 12));

        return textArea;
    }

    private void setDarkMode() {
        changeComponentColors(textArea);

        menuBar.setUI(new BasicMenuBarUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                if (darkMode) g.setColor(Color.black);
                else g.setColor(Color.white);
                g.fillRect(0, 0, c.getWidth(), c.getHeight());
            }
        });

        MenuElement[] menus = menuBar.getSubElements();
        for (MenuElement menuElement : menus) {
            JMenu menu = (JMenu) menuElement.getComponent();
            changeComponentColors(menu);
            menu.setOpaque(true);

            MenuElement[] menuElements = menu.getSubElements();
            for (MenuElement popupMenuElement : menuElements) {
                JPopupMenu popupMenu = (JPopupMenu) popupMenuElement.getComponent();
                popupMenu.setBorder(null);

                MenuElement[] menuItens = popupMenuElement.getSubElements();
                for (MenuElement menuItemElement : menuItens) {
                    JMenuItem menuItem = (JMenuItem) menuItemElement.getComponent();
                    changeComponentColors(menuItem);
                    menuItem.setOpaque(true);
                }
            }
        }

    }

    private void changeComponentColors(Component comp) {
        if (darkMode) {
            comp.setBackground(Color.black);
            comp.setForeground(Color.white);
        } else {
            comp.setBackground(Color.white);
            comp.setForeground(Color.black);
        }
    }

}