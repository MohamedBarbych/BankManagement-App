package com.gsb.dao.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
//import jakarta.persistence.*;
@Entity
@DiscriminatorValue("T")
public class Virement extends Operation {
}
