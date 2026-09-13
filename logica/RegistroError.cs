namespace TicketsSoporte.logica
{
    public class RegistroError
    {
        public string strTipo;
        public string strDescripcion;
        public string strImpacto;
        public DateTime dtmFecha;

        public RegistroError(string strTipo, string strDescripcion, string strImpacto)
        {
            this.strTipo = strTipo;
            this.strDescripcion = strDescripcion;
            this.strImpacto = strImpacto;
            dtmFecha = DateTime.Now;
        }

        public override string ToString()
        {
            return $"[{dtmFecha:dd/MM/yyyy HH:mm}] Tipo: {strTipo} | Impacto: {strImpacto} | {strDescripcion}";
        }
    }
}
