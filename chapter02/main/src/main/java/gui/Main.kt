package gui

import gui.store.Action
import gui.store.Store
import gui.view.GameDesk
import gui.view.MainControlPanel
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.JFrame
import javax.swing.WindowConstants


fun main(args: Array<String>) {
    JFrame.setDefaultLookAndFeelDecorated(false)
    javax.swing.SwingUtilities.invokeLater {
        val frame = JFrame("21 game")
        frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE

        val pane = frame.contentPane
        pane.layout = GridBagLayout()
        val c = GridBagConstraints()

        val gameDesk = GameDesk()
        Store.getInstance().subscribe(Action.TYPE_STAGE) { gameDesk.refreshStage() }
        c.fill = GridBagConstraints.BOTH
        c.weightx = 1.0
        c.weighty = 99.0
        c.gridx = 0
        c.gridy = 0
        pane.add(gameDesk, c)
        val controlPanel = MainControlPanel()
        c.fill = GridBagConstraints.BOTH
        c.weighty = 1.0
        c.gridx = 0
        c.gridy = 1
        pane.add(controlPanel, c)

        //Display the window.
        frame.pack()
        frame.setSize(800, 600)
        frame.setLocationRelativeTo(null)
        frame.isVisible = true
        frame.isResizable = false
    }
}