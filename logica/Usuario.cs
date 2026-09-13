namespace TicketsSoporte.logica
{
    public abstract class Usuario
    {
        public string strId;
        public string strNombre;
        public string strEmail;

        public Usuario(string strId, string strNombre, string strEmail)
        {
            this.strId = strId;
            this.strNombre = strNombre;
            this.strEmail = strEmail;
        }

        public abstract void mostrarInformacion();
    }
}
