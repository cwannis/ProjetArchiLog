package biblio.document.DocumentsState;

import biblio.document.DocumentState;
import biblio.document.exception.RetourException;

public class DocumentEmprunter extends DocumentState {

    public DocumentState retour() throws RetourException {
        return new DocumentLibre();
    }

}
