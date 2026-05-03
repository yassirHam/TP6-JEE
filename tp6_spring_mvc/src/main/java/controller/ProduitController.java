package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.Produit;
import services.ProduitMetier;

@Controller
public class ProduitController {
    
    @Autowired
    ProduitMetier services;

    @RequestMapping(value={"/index", "/index.aspx"})
    public String pageIndex(Model model) {
        model.addAttribute("listeProduit", services.getAllProduits());
        return "produits";
    }

    @RequestMapping(value={"/searchProduct", "/searchProduct.aspx"})
    public String searchProduct(Model model, @RequestParam(value="idProduit", required=false) Long id) {
        if (id != null) {
            List<Produit> liste = new ArrayList<>();
            Produit p = services.getProduitById(id);
            if (p != null) {
                liste.add(p);
            }
            model.addAttribute("listeProduit", liste);
            model.addAttribute("idProduit", id);
        } else {
            model.addAttribute("listeProduit", services.getAllProduits());
        }
        return "produits";
    }

    @RequestMapping(value={"/addProduct", "/addProduct.aspx"})
    public String addProduct(Model model, Produit p) {
        services.addProduit(p);
        model.addAttribute("listeProduit", services.getAllProduits());
        return "produits";
    }

    @RequestMapping(value={"/deleteProduit", "/deleteProduit.aspx"})
    public String supprimerProduit(Model model, @RequestParam Long id) {
        services.deleteProduit(id);
        model.addAttribute("listeProduit", services.getAllProduits());
        return "produits";
    }

    @RequestMapping(value={"/editProduit", "/editProduit.aspx"}, method=RequestMethod.GET)
    public String editProduit(Model model, @RequestParam Long id) {
        Produit p = services.getProduitById(id);
        model.addAttribute("produitEdit", p);
        model.addAttribute("listeProduit", services.getAllProduits());
        return "produits";
    }

    @RequestMapping(value={"/updateProduit", "/updateProduit.aspx"}, method=RequestMethod.POST)
    public String updateProduitPost(Model model, Produit p) {
        services.updateProduit(p);
        model.addAttribute("listeProduit", services.getAllProduits());
        return "produits";
    }
}
